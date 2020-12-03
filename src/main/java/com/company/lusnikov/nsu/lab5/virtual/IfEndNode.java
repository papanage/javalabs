package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

@Builder
public class IfEndNode implements IoNode {
    IoComputer computer;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        System.out.println("IF END");
        Label label = context.getIflabel().pop();
        methodVisitor.visitLabel(label);
        computer.setStackFrame(methodVisitor);

    }
}
