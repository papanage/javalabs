package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Getter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

public class IoComputer {

    public  ContextVars context = new ContextVars();
    @Getter
    List<IoNode> prog = new ArrayList<>();
    public void generate(MethodVisitor methodVisitor) {

        prog.forEach((x) -> {
            System.out.println(context.getCountVars());
            x.doSomething(methodVisitor, context);
        });
    }

    public Object[] getStackFrame() {
        Integer count = context.getCountVars();
        Object[] objects = new Object[count + 1];
        objects[0] = "[Ljava/lang/String;";
        int j = 0;
        for (int i = 0; i < prog.size(); i++) {
            if (prog.get(i).equals(this)) break;
            if (prog.get(i) instanceof DeclVarNode) {
                j++;
                DeclVarNode declVarNode = (DeclVarNode) prog.get(i);
                if (declVarNode.s == null) {
                    System.out.println("java/lang/Integer");
                    objects[j] = "java/lang/Integer";
                } else {
                    System.out.println("java/lang/String");
                    objects[j] = "java/lang/String";
                }
            }
        }
        context.setIsAfterDecl(true);
        return objects;
    }

    public void setStackFrame(MethodVisitor methodVisitor) {
        if (!context.getIsAfterDecl()) {
            methodVisitor.visitFrame(Opcodes.F_FULL,context.getCountVars() + 1, getStackFrame() , 0, null);
        }
        else {
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
    }

}
