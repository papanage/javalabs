package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

@Builder
public class VarNode implements IoNode{
    String source;
    String target;
    Integer i;
    String s;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        if (!context.getIsDecl()) {
            context.setOperCount2(context.getOperCount2() + 1);;
            context.setIsDecl(true);
        }

        System.out.println("VAR Change: " + source + " " +target + " " + i + " " + s);
        if (target != null) {
            int source_pos;
            int target_pos;
            if (context.getStrings().containsKey(source)) {
                source_pos = context.getStrings().get(source).getPos();
                target_pos = context.getStrings().get(target).getPos();

            } else {
                source_pos = context.getIntegers().get(source).getPos();
                target_pos = context.getIntegers().get(target).getPos();
            }

            methodVisitor.visitVarInsn(ALOAD, target_pos);
            methodVisitor.visitVarInsn(ASTORE, source_pos);

        } else if (s != null) {
            methodVisitor.visitLdcInsn(s);
            int  source_pos = context.getStrings().get(source).getPos();
            methodVisitor.visitVarInsn(ASTORE, source_pos);

        } else {
            System.out.println("FSD432423");
            methodVisitor.visitLdcInsn(i);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            int  source_pos = context.getIntegers().get(source).getPos();
            methodVisitor.visitVarInsn(ASTORE, source_pos);
        }
    }
}
