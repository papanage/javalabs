package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Builder;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;
import java.util.Map;

@Builder
public class LabelNode implements IoNode{
    Integer id;
    Label label;
    @Override
    public void doSomething(MethodVisitor methodVisitor, ContextVars context) {
        System.out.println("LABEL COMPUTE " + id);
        Integer count = context.getCountVars();
        Object[] objects = new Object[count + 1];
        objects[0] = "[Ljava/lang/String;";
        for (int i = 0; i < count; i++) {
           for (Map.Entry<String, ContextVars.IntWithPos> entry : context.getIntegers().entrySet()) {
               if (entry.getValue().getPos() == i+1) {
                   objects[i+1] = "java/lang/Integer";
               }
           }
            for (Map.Entry<String, ContextVars.StringWithPos> entry : context.getStrings().entrySet()) {
                if (entry.getValue().getPos() == i+1) {
                    objects[i+1] = "java/lang/String";
                }
            }
        }

        System.out.println("COUNT " +count);
        Arrays.stream(objects).forEach(System.out::println);
        System.out.println("count end");
        methodVisitor.visitLabel(label);
        //if (id != 2) {
          //  methodVisitor.visitFrame(Opcodes.F_APPEND,count, objects, 0, null);
            methodVisitor.visitFrame(Opcodes.F_FULL,count+1, objects, 0, null);
        //}
        //else         methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

    }
}
