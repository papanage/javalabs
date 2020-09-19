package com.company.lusnikov.nsu.lab1.utils;

import com.company.lusnikov.nsu.lab1.ConstantNode;

import java.util.Vector;

public class Ref extends ResolveData {

    public Ref(String toResolve) {
        super(toResolve);
    }

    @Override
    public String getData() {
        return  "#" + toResolve;
    }

    @Override
    public String resolve(Vector<ConstantNode> nodes) {
        StringBuilder stringBuilder = new StringBuilder();
        //System.out.println("index = " + Integer.parseInt(toResolve));
        //System.out.println("size = " + nodes.size());
        ConstantNode c = nodes.get(Integer.parseInt(toResolve) - 1);
        for (ResolveData s : c.getRefs()){
          stringBuilder.append(s.resolve(nodes));
        }
        return stringBuilder.toString();
    }
}
