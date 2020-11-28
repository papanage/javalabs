package com.company.lusnikov.nsu.lab5.virtual;


import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

@Builder
public class PrintNode implements IoNode{
    String varname;
    String s;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
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
