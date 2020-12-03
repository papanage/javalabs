package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.GOTO;

@Builder
public class GotoNode implements IoNode {
    List<IoNode> prog = new ArrayList<>();
    Integer id;
    IoComputer computer;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        Label go = context.getLabelList().get(id);
        methodVisitor.visitJumpInsn(GOTO, go);
        methodVisitor.visitLabel(new Label());
        computer.setStackFrame(methodVisitor);

    }
}
