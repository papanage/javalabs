package com.company.lusnikov.nsu.lab2;



import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_STATIC;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_SUPER;
import static jdk.internal.org.objectweb.asm.Opcodes.ALOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.GETSTATIC;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static jdk.internal.org.objectweb.asm.Opcodes.RETURN;


public class GuessNumber {
    public static void main(String[] args) throws Exception{
        File file = new File("C:\\Users\\Vasya\\IdeaProjects\\javalabs\\resources\\Hello.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(dump());
    }
    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv, scan;
        AnnotationVisitor av0;

        cw.visit(49,
            ACC_PUBLIC + ACC_SUPER,
            "Hello",
            null,
            "java/lang/Object",
            null);

        cw.visitSource("Hello.java", null);




        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);

            mv.visitFieldInsn(GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");

            mv.visitLdcInsn("I`ve thought a number, try to guess!");

            mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V");



            mv.visitFieldInsn(GETSTATIC,
                "java/lang/System",
                "in",
                "Ljava/io/InputStream;");

            mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/InputStream",
                "read",
                "()I");


/*

*/
           // mv.visitLdcInsn(System.in);


            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }

        cw.visitEnd();

        return cw.toByteArray();
    }
}
