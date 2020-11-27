package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GOTO;

@Builder
public class GotoNode implements IoNode {
    Integer id;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {

        Label go = context.getLabelList().get(id);
        methodVisitor.visitJumpInsn(GOTO, go);
    }
}
