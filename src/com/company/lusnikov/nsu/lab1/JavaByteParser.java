package com.company.lusnikov.nsu.lab1;

import com.company.lusnikov.nsu.lab1.classreader.ClassReader;
import com.company.lusnikov.nsu.lab1.utils.data.ResolveData;
import com.company.lusnikov.nsu.lab1.utils.node.ConstantNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class JavaByteParser {
    public static void ShowConstant(File file) throws Exception {

        System.out.println(file.getPath());
       ClassReader reader = new ClassReader();
        //System.out.println("ho");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //System.out.println("ho");
       Vector<ConstantNode> nodes = reader.read(fileInputStream);

       int v = 1;
       for (ConstantNode node : nodes){
           if (node.getTag() == null){
               v++;
               continue;
           }
           System.out.println(v++ + ". " + node.getTag() + ": ");
           for (ResolveData h : node.getRefs()){
               System.out.print(h.getData());
           }
           System.out.println(" \\\\" + ConstantNode.resolveNote(nodes, nodes.indexOf(node)));
           System.out.println("\n");
       }
    }

}
