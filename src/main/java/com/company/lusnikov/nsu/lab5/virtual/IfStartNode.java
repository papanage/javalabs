package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.Condition;
import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.IF_ICMPGT;

@Builder
public class IfStartNode implements IoNode {
    Condition condition;
    IoComputer computer;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        Label label = new Label();
        context.getIflabel().push(label);
        if (condition.getVar1() != null){
            int pos = context.getIntegers().get(condition.getVar1()).getPos();
            methodVisitor.visitVarInsn(ALOAD, pos);
            pos = context.getIntegers().get(condition.getVar2()).getPos();
            methodVisitor.visitVarInsn(ALOAD, pos);
        }
        methodVisitor.visitJumpInsn(IF_ICMPGT, label);
        Label body = new Label();
        methodVisitor.visitLabel(body);
        computer.setStackFrame(methodVisitor);
    }
}
