package com.company.lusnikov.nsu.lab1.fab;

import com.company.lusnikov.nsu.lab1.ConstantNode;
import com.company.lusnikov.nsu.lab1.tagshandlers.TagsHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class HandleTagsController {

    public static Vector<ConstantNode> compute(FileInputStream inputStream) throws Exception{
        HandlerFabricy handlerFabricy = new HandlerFabricy();
        Vector<ConstantNode> pool = new Vector<>();

        int tag;
        int y = computeSize(inputStream);
        while (--y != 0){
            tag = inputStream.read();
            pool.add(handlerFabricy.getTagsHandler(tag).compute(inputStream));

        }
        return pool;

    }

    private static int computeSize(FileInputStream inputStream) throws IOException {
        int b1 = inputStream.read();
        int b2 = inputStream.read();
        return ((0xFF & b1) << 8) | (0xFF & b2);
    }
}
