package com.company.lusnikov.nsu.lab1.utils;

import com.company.lusnikov.nsu.lab1.ConstantNode;

import java.util.Vector;

public abstract class ResolveData {
    public String getToResolve() {
        return toResolve;
    }

    String toResolve;

    public ResolveData(String toResolve){

        //System.out.println(toResolve);
        this.toResolve = toResolve;
    }
    public abstract String getData();
    public abstract String resolve(Vector<ConstantNode> nodes);
}
