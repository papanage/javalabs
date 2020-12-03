package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

@Builder
public class IfEndNode implements IoNode {
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        Label label = context.getIflabel().pop();
        methodVisitor.visitLabel(label);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

    }
}
