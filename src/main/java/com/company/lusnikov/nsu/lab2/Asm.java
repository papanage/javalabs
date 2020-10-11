package com.company.lusnikov.nsu.lab2;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;

public class Asm {
    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[]{GuessGame2.class.getName()});
    }
}
