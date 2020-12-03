package com.company.lusnikov.nsu.lab5;

public class Test {
    public static void main(String[] args) {
        Integer a = Math.toIntExact(Math.round(Math.random()));
        Integer b = Math.toIntExact(Math.round(Math.random()));
       if (a > b) {
           System.out.println(a);
       }
        System.out.println(b);
    }
}
