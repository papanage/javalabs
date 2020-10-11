package com.company.lusnikov.nsu.lab2;

import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GuessNumberAsm {



    public static void main(String[] args) throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "GuessGame",
                null, Type.getInternalName(AdviceAdapter.class), null);
        classWriter.visitEnd();
        //save to file
        byte[] bytes = classWriter.toByteArray();
        //System.out.println(GuessNumberAsm.class.getResource("/GuessGame").getPath());
        Files.write(Paths.get("C:\\Users\\Vasya\\IdeaProjects\\javalabs\\src\\main\\resources\\GuessGame"), bytes);
    }

}
