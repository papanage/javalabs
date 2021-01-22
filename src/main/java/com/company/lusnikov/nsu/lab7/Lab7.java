package com.company.lusnikov.nsu.lab7;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Lab7 {

    @State(Scope.Thread)
    public static class Res {
        String[] strings = new String[]{
                "121212",
                "werewrew",
                "121212fff",
                "ffff3333222",
                "22221111",
                "222222222ddd"
        };
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testParse(Res res) {
        for (String s : res.strings) {
            isNumberParse(s);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testCircle(Res res) {
        for (String s : res.strings) {
            isNumberCircle(s);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testReg(Res res) {
        for (String s : res.strings) {
            isNumberReg(s);
        }
    }

    public static boolean isNumberParse(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumberCircle(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumberReg(String s) {
        return s.matches("^[1-9]+$");
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Res res = new Res();
        for (String s : res.strings) {
            System.out.println(isNumberParse(s));
            System.out.println(isNumberCircle(s));
            System.out.println(isNumberReg(s));

            System.out.println();

        }
        org.openjdk.jmh.Main.main(args);
    }
}
