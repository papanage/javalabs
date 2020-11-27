package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

@Builder
public class LabelNode implements IoNode{
    Integer id;
    Label label;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        methodVisitor.visitLabel(label);
    }
}