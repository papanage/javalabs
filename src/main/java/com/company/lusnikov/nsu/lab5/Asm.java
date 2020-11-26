package com.company.lusnikov.nsu.lab5;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;

public class Asm {
    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[]{Test.class.getName()});
    }
}