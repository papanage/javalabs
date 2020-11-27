package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

@Builder
public class DeclVarNode implements IoNode{
    String name;
    Integer i;
    String s;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        if (!context.getIsDecl()) {
            context.setOperCount2(context.getOperCount2() + 1);;
            context.setIsDecl(true);
        }

        System.out.println("DECLVAR: " + name + " " + i + " " + s + context.getCountVars());
        context.setCountVars(context.getCountVars() + 1);
        if (i == null) {
            context.getStrings().put(name, new ContextVars.StringWithPos(s, context.getCountVars()));
            methodVisitor.visitLdcInsn(s);
        } else {
            context.getIntegers().put(name, new ContextVars.IntWithPos(i, context.getCountVars()));
            methodVisitor.visitLdcInsn(i);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        }
        methodVisitor.visitVarInsn(ASTORE, context.getCountVars());
    }

}
