package com.company.lusnikov.nsu.lab5.virtual;

import com.company.lusnikov.nsu.lab5.ContextVars;
import lombok.Getter;
import org.objectweb.asm.MethodVisitor;

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

}
