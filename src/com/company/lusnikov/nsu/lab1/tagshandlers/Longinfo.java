package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.utils.node.ConstantNode;
import com.company.lusnikov.nsu.lab1.utils.node.Tags;
import com.company.lusnikov.nsu.lab1.utils.data.ResolveData;
import com.company.lusnikov.nsu.lab1.utils.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Longinfo extends TagsHandler {
    {
        tag = Tags.LONG;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        int highb = fourByteInInt((byte)inputStream.read(), (byte)inputStream.read(),
                (byte)inputStream.read(), (byte)inputStream.read());
        int lowb = fourByteInInt((byte)inputStream.read(), (byte)inputStream.read(),
                (byte)inputStream.read(), (byte)inputStream.read());
        Long l = ((long) highb << 32) + lowb;

        refs.add(new StaticData(l.toString()));

        return new ConstantNode(tag, refs);
    }
}
