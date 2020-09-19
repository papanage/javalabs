package com.company.lusnikov.nsu.lab1;

import com.company.lusnikov.nsu.lab1.fab.HandleTagsController;
import com.company.lusnikov.nsu.lab1.utils.ResolveData;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

public class JavaByteParser {
    public static void ShowConstant(File file) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(file);

       for (int i = 0; i < 8; i++ ){
           fileInputStream.read();

       }
       int v = 1;
       Vector<ConstantNode> nodes = HandleTagsController.compute(fileInputStream);

       for (ConstantNode node : nodes){
           System.out.println(v++ + ". " + node.tag + ": ");
           for (ResolveData h : node.refs){
               System.out.print(h.getData());
           }
           System.out.println(" \\\\" + ConstantNode.resolveNote(nodes, nodes.indexOf(node)));
           System.out.println("\n");


       }
    }


}
