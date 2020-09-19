package com.company.lusnikov.nsu.lab1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public final class ConstantDump {
    private ConstantDump(){
    }

    public static void main(String[] args) throws Exception {
       // System.out.println(Integer.parseInt("1"));
        System.out.println(Paths.get(args[0]));
        JavaByteParser.ShowConstant(new File(args[0]));
    }
}
