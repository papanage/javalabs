package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.Condition;
import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.IF_ICMPNE;

@Builder
public class IfStartNode implements IoNode {
    Condition condition;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        Label label = new Label();
        context.getIflabel().push(label);
        methodVisitor.visitJumpInsn(IF_ICMPNE, label);

    }
}
