package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.GOTO;

@Builder
public class GotoNode implements IoNode {
    List<IoNode> prog = new ArrayList<>();
    Integer id;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        Integer count = context.getCountVars();
        Object[] objects = new Object[count + 1];
        objects[0] = "[Ljava/lang/String;";
        System.out.println("GOTO to " + id);
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


        Label go = context.getLabelList().get(id);
        methodVisitor.visitJumpInsn(GOTO, go);
        methodVisitor.visitLabel(new Label());
        methodVisitor.visitFrame(Opcodes.F_FULL,count+1, objects , 0, null);
    }
}
