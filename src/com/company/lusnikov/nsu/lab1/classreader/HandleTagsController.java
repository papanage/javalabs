package com.company.lusnikov.nsu.lab1.classreader;

import com.company.lusnikov.nsu.lab1.tagshandlers.TagsHandler;
import com.company.lusnikov.nsu.lab1.common.node.ConstantNode;
import com.company.lusnikov.nsu.lab1.common.node.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class HandleTagsController {

    public static Vector<ConstantNode> compute(InputStream inputStream) throws Exception{
        HandlerFabricy handlerFabricy = new HandlerFabricy();
        Vector<ConstantNode> pool = new Vector<>();

        int tag;
        int y = computeSize(inputStream);
        //System.out.println("real size = " + (y - 1));
        while (--y != 0){
            tag = inputStream.read();
            TagsHandler t = handlerFabricy.getTagsHandler(tag);

            pool.add(handlerFabricy.getTagsHandler(tag).compute(inputStream));

            if (t.tag.equals(Tags.LONG) || t.tag.equals(Tags.DOUBLE)){
                pool.add(new ConstantNode(null, null));
                y--;
            }
        }
        //System.out.println("size = " + pool.size());
        return pool;

    }

    private static int computeSize(InputStream inputStream) throws IOException {
        int b1 = inputStream.read();
        int b2 = inputStream.read();
        return ((0xFF & b1) << 8) | (0xFF & b2);

    }
}
