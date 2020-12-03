package com.company.lusnikov.nsu.lab5.virtual;


import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

@Builder
public class PrintNode implements IoNode{
    String varname;
    String s;
    List<IoNode> prog = new ArrayList<>();
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {

        Integer count = context.getCountVars();
        Object[] objects = new Object[count + 1];
        objects[0] = "[Ljava/lang/String;";
        int j = 0;
        for (int i = 0; i < prog.size(); i++) {
            if (prog.get(i).equals(this)) break;
            if (prog.get(i) instanceof DeclVarNode) {
                j++;
                DeclVarNode declVarNode = (DeclVarNode) prog.get(i);
                if (declVarNode.s == null) {
                    System.out.println("java/lang/Integer");
                    objects[j] = "java/lang/Integer";
                } else {
                    System.out.println("java/lang/String");
                    objects[j] = "java/lang/String";
                }
            }
        }

        if (context.getIsContextChange()) methodVisitor.visitFrame(Opcodes.F_FULL,count+1, objects , 0, null);



        System.out.println("PRINT: " + varname + " " + s);
        if (!context.getIsPrint()) {
            context.setOperCount2(context.getOperCount2() + 1);;
            context.setIsPrint(true);
        }

        int pos;
        if (varname == null) {
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn(s);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        } else {
            if (context.getStrings().containsKey(varname)) {
                pos = context.getStrings().get(varname).getPos();
                methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                methodVisitor.visitVarInsn(ALOAD, pos);
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            } else {
                pos = context.getIntegers().get(varname).getPos();
                methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                methodVisitor.visitVarInsn(ALOAD, pos);
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
            }
        }
    }

}
