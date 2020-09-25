package com.company.lusnikov.nsu.lab1.utils.data;

import com.company.lusnikov.nsu.lab1.utils.node.ConstantNode;

import java.sql.Connection;
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
        System.out.println("Constant pool: git ");
        //System.out.println("hello" + getData() + " " + (Integer.parseInt(toResolve) - 1));
        StringBuilder stringBuilder = new StringBuilder();
        if ((Integer.parseInt(toResolve) - 1) >= nodes.size()) return stringBuilder.toString();
        ConstantNode c = nodes.get(Integer.parseInt(toResolve) - 1);
        for (ResolveData s : c.getRefs()){
          stringBuilder.append(s.resolve(nodes));
        }
        return stringBuilder.toString();
    }
}
