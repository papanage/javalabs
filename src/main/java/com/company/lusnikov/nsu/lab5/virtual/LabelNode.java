package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

@Builder
public class LabelNode implements IoNode{
    Integer id;
    Label label;
    IoComputer computer;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        System.out.println("LABEL COMPUTE " + id);

        methodVisitor.visitLabel(label);

        if (!context.getIsAfterDecl()) {
            methodVisitor.visitFrame(Opcodes.F_FULL,context.getCountVars() + 1, computer.getStackFrame() , 0, null);
        }
        else {
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }

        context.setIsContextChange(false);
    }
}
