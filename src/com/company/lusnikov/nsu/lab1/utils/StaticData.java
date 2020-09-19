package com.company.lusnikov.nsu.lab1.utils;

import com.company.lusnikov.nsu.lab1.ConstantNode;

import java.util.Vector;

public class StaticData extends ResolveData {


    public StaticData(String toResolve) {
        super(toResolve);
    }

    @Override
    public String getData() {
        return toResolve;
    }

    @Override
    public String resolve(Vector<ConstantNode> nodes) {
       return toResolve;
    }
}
