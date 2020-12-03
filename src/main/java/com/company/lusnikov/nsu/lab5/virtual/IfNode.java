package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.MethodVisitor;

@Builder
public class IfNode implements IoNode{
    String s1;
    String s2;
    Integer i1;
    Integer i2;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        if (i1 != null) {

        }

    }
}
