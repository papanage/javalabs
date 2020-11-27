package com.company.lusnikov.nsu.lab5;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        Integer a = random.nextInt();
        Integer b = random.nextInt();

        if (a > b) {
            System.out.println(a);
        }
    }
}
