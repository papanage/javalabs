package com.company.lusnikov.nsu.lab5;

import gramma.IoParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.RecordComponentVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_8;


public class Creator extends gramma.IoBaseListener{

    ContextVars current = new ContextVars();
    Integer countVars = 0;
    MethodVisitor  methodVisitor;
    Boolean isPrint = false;
    Boolean isDecl = false;
    Integer operCount = 0;

    @Override
    public void enterProg(IoParser.ProgContext ctx) {
        System.out.println("ENTER: prog");
        super.enterProg(ctx);
    }

    @Override
    public void enterExpr(IoParser.ExprContext ctx) {
        System.out.println("ENTER: exp" + ctx.toString());
        super.enterExpr(ctx);
    }

    @Override
    public void exitDeclvar(IoParser.DeclvarContext declvarContext) {
        String varname = declvarContext.VARNAME().getSymbol().getText();
        String zn;
        if (declvarContext.INT() == null) {
            countVars++;
            zn = declvarContext.STRING().getText();
            zn = zn.substring(1, zn.length() - 1);
            current.getStrings().put(varname, new ContextVars.StringWithPos(zn, countVars));
            methodVisitor.visitLdcInsn(zn);
        } else {
            countVars++;
            zn = declvarContext.INT().getText();
            current.getIntegers().put(varname, new ContextVars.IntWithPos(Integer.parseInt(zn), countVars));
            methodVisitor.visitLdcInsn(Integer.parseInt(zn));
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        }
        if (!isDecl) {
            operCount++;
            isDecl = true;
        }
        methodVisitor.visitVarInsn(ASTORE, countVars);
        System.out.println("vardecl:" + varname + " zn: "  +zn +  "count: " + countVars);
        super.exitDeclvar(declvarContext);
    }

    @Override
    public void exitPrint(IoParser.PrintContext ctx) {
        if (ctx.VARNAME() == null) {
            System.out.println("PRINT: " + ctx.toString());
            return;
        }
        int pos = -1;
        if (current.getStrings().containsKey(ctx.VARNAME().getText())) {
            pos = current.getStrings().get(ctx.VARNAME().getText()).getPos();
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitVarInsn(ALOAD, pos);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        } else {
            pos = current.getIntegers().get(ctx.VARNAME().getText()).getPos();

            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitVarInsn(ALOAD, pos);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
        }

        System.out.println("print:" + ctx.VARNAME().getText() + " zn: "  + pos);
        if (!isPrint) {
            operCount++;
            isPrint = true;
        }
        super.enterPrint(ctx);
    }

    public static void main(String[] args) throws Exception {

        Creator creator = new Creator();
        creator.test2();
    }

    public void test1() throws Exception {
        gramma.IoLexer lexer = new gramma.IoLexer(CharStreams.fromString(" var a=21\n" +
                " if (a>3) then\n" +
                " var d=3\n" +
                "end\n"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gramma.IoParser parser = new gramma.IoParser(tokens);
        gramma.IoParser.ProgContext progContext = parser.prog();
        File file = new File("Code1.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(dump(parser, "Code2"));
    }

    public void test2() throws Exception {
        String code = FileUtils.readFileToString(
                new File(getClass().getResource("/test").toURI().getPath()), StandardCharsets.UTF_8);
        gramma.IoLexer lexer = new gramma.IoLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gramma.IoParser parser = new gramma.IoParser(tokens);

        File file = new File("Code2.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(dump(parser, "Code2"));
    }


    public void create(gramma.IoParser parser, MethodVisitor methodVisitor) {

        parser.addParseListener(this);
        parser.prog();



        methodVisitor.visitInsn(RETURN);
        Label last = new Label();
        methodVisitor.visitLabel(last);
        methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, current.labelList.get(0), last, 0);

        current.getIntegers().forEach(
                (k, v)->
                        methodVisitor.visitLocalVariable(k, "Ljava/lang/Integer;", null, current.labelList.get(0), last, v.getPos()));

        current.getStrings().forEach(
                (k, v)->
                        methodVisitor.visitLocalVariable(k, "Ljava/lang/String;", null, current.labelList.get(0), last, v.getPos()));


        methodVisitor.visitMaxs(operCount, countVars+2);
        System.out.println("STACK : " + operCount + " "  + (countVars+2));
        methodVisitor.visitEnd();
    }
    private void print() {

    }


    public byte[] dump (gramma.IoParser parser, String name) throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, name, null, "java/lang/Object", null);

        classWriter.visitSource("Test.java", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(3, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "L"+name+";", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }

        methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        current.labelList.add(label0);


        create(parser, methodVisitor);

        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
