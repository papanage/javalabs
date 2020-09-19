package com.company.lusnikov.nsu.lab1.utils.node;

import com.company.lusnikov.nsu.lab1.utils.data.ResolveData;

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
       // System.out.println("new tag");
        /*System.out.println(tag.getId() );
        for (ResolveData h : refs){
            System.out.print(h.getData());
        }
        System.out.println("\n");
*/

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
