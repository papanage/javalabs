package com.company.lusnikov.nsu.lab5.virtual;


import com.company.lusnikov.nsu.lab5.ContextVars;
import org.objectweb.asm.MethodVisitor;

public interface  IoNode {
    void doSomething(MethodVisitor methodVisitor, ContextVars contextVars);
}
