package com.company.lusnikov.nsu.lab5;

import com.company.lusnikov.nsu.lab5.virtual.DeclVarNode;
import com.company.lusnikov.nsu.lab5.virtual.GotoNode;
import com.company.lusnikov.nsu.lab5.virtual.IfStartNode;
import com.company.lusnikov.nsu.lab5.virtual.IoComputer;
import com.company.lusnikov.nsu.lab5.virtual.LabelNode;
import com.company.lusnikov.nsu.lab5.virtual.PrintNode;
import com.company.lusnikov.nsu.lab5.virtual.VarNode;
import gramma.IoParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_8;


public class Creator extends gramma.IoBaseListener{

    MethodVisitor methodVisitor;
    IoComputer computer = new IoComputer();
    //Label last;

    @Override
    public void enterProg(IoParser.ProgContext ctx) {
        System.out.println("ENTER: prog");
        super.enterProg(ctx);
    }

    @Override
    public void exitExpr(IoParser.ExprContext ctx) {
        System.out.println("ENTER: exp" + ctx.toString());
        super.enterExpr(ctx);
    }

    @Override
    public void exitDeclvar(IoParser.DeclvarContext declvarContext) {

        String varname = declvarContext.VARNAME().getSymbol().getText();
        String zn;
        if (declvarContext.INT() == null) {
            zn = declvarContext.STRING().getText();
            zn = zn.substring(1, zn.length() - 1);
            computer.getProg().add(DeclVarNode
                    .builder()
                    .s(zn)
                    .name(varname)

                    .build());

        } else {
            zn = declvarContext.INT().getText();
            computer.getProg().add(DeclVarNode
                    .builder()
                    .i(Integer.parseInt(zn))
                    .name(varname)
                    .build());
        }

        super.exitDeclvar(declvarContext);
    }

    @Override
    public void exitPrint(IoParser.PrintContext ctx) {


        if (ctx.VARNAME() == null) {
            String zn = ctx.STRING().getText();
            zn = zn.substring(1, zn.length() - 1);
            computer.getProg().add(PrintNode
                    .builder()
                    .s(zn)
                    .computer(computer)
                    .build());
        } else {
            computer.getProg().add(PrintNode
                    .builder()
                    .varname(ctx.VARNAME().toString())
                    .computer(computer)
                    .build());
        }

        super.enterPrint(ctx);
    }


    @Override
    public void exitCond(IoParser.CondContext ctx) {
        System.out.println("dfgdfgdsfgdsgdsg");
         //
         List<IoParser.OrderableContext> context = ctx.orderable();

         System.out.println(context.get(0).VARNAME());
         System.out.println(context.get(1).VARNAME());
        Condition condition = new Condition();
        condition.setVar1(ctx.orderable(0).VARNAME().toString());
        condition.setVar2(ctx.orderable(1).VARNAME().toString());

       computer.getProg().add(IfStartNode
                .builder()
                .condition(condition)
                .computer(computer)
                .build());

        super.exitCond(ctx);
    }

    @Override
    public void exitIf_(IoParser.If_Context ctx) {
        if (ctx.cond().orderable().get(0).INT() != null) {
            /*computer.getProg().add(IfEndNode
                    .builder()
                    .computer(computer)
                    .build());

             */
        }
        super.exitIf_(ctx);
    }

    @Override
    public void exitLabel(IoParser.LabelContext ctx) {
        System.out.println("LABEL: " + ctx.INT().toString());
        Label label = new Label();
        computer.getProg().add(LabelNode
                .builder()
                .id(Integer.parseInt(ctx.INT().toString()))
                .label(label)
                .computer(computer)
                .build());
        computer.context.getLabelIntegerMap().put(label, computer.context.getCountVars());
        computer.context.getLabelList().put(Integer.parseInt(ctx.INT().toString()), label);
       // last = label;
        super.exitLabel(ctx);
    }

    @Override
    public void exitGoto_(IoParser.Goto_Context ctx) {
        int id = Integer.parseInt(ctx.INT().toString());
        computer.getProg().add(GotoNode
                .builder()
                .id(id)
                .prog(computer.getProg())
                .computer(computer)
                .build());
        super.exitGoto_(ctx);
    }

    @Override
    public void exitVar(IoParser.VarContext ctx) {
        if (ctx.VARNAME(1) != null) {
            computer.getProg().add(VarNode
                    .builder()
                    .source(ctx.VARNAME(0).toString())
                    .target(ctx.VARNAME(1).toString())
                    .build());
        } else if (ctx.STRING() != null) {
            computer.getProg().add(VarNode
                    .builder()
                    .source(ctx.VARNAME(0).toString())
                    .s(ctx.STRING().toString())
                    .build());
        } else {
            computer.getProg().add(VarNode
                    .builder()
                    .source(ctx.VARNAME(0).toString())
                    .i(Integer.parseInt(ctx.INT().toString()))
                    .build());
        }

        super.exitVar(ctx);
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

        computer.generate(methodVisitor);



        Label last = new Label();
        methodVisitor.visitLabel(last);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(RETURN);
       // methodVisitor.visitFrame(Opcodes.F_APPEND,2, new Object[] {"java/lang/Integer", "java/lang/Integer"}, 0, null);
        //

        methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, computer.context.getLabelList().get(0), last, 0);

        computer.context.getIntegers().forEach(
                (k, v)-> {
                    System.out.println("INT: " + k + " " + v);
                    methodVisitor.visitLocalVariable(k, "Ljava/lang/Integer;", null, computer.context.getLabelList().get(0), last, v.getPos());
                });


        computer.context.getStrings().forEach(
                (k, v)->
                        methodVisitor.visitLocalVariable(k, "Ljava/lang/String;", null, computer.context.getLabelList().get(0), last, v.getPos()));


        methodVisitor.visitMaxs(computer.context.getOperCount2(), computer.context.getCountVars()+2);
        System.out.println("STACK : " + computer.context.getOperCount2() + " "  + (computer.context.getCountVars()+2));
        methodVisitor.visitEnd();
    }



    public byte[] dump (gramma.IoParser parser, String name) throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, name, null, "java/lang/Object", null);

        classWriter.visitSource("Code2.java", null);

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
       // methodVisitor.visitFrame(Opcodes.F_APPEND,0, new Object[] {}, 0, null);



        computer.context.getLabelList().put(0, label0);

        create(parser, methodVisitor);

        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
