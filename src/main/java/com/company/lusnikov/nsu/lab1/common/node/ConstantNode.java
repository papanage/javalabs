package com.company.lusnikov.nsu.lab1.common.node;

import com.company.lusnikov.nsu.lab1.common.data.ResolveData;

import java.util.Vector;

public class ConstantNode {
    Tags tag;
    Vector<ResolveData> refs;

    public Tags getTag() {
        return tag;
    }

    public Vector<ResolveData> getRefs() {
        return refs;
    }


    public ConstantNode(Tags tag, Vector<ResolveData> refs) {
        this.tag = tag;
        this.refs = refs;

    }

    public static String resolveNote(Vector<ConstantNode> nodes, int pos){
        StringBuilder stringBuilder = new StringBuilder();
        //System.out.println("error: " + nodes.size() < pos);
        for (ResolveData s : nodes.get(pos).refs){
            stringBuilder.append(s.resolve(nodes));
        }
        return stringBuilder.toString();
    }


}
