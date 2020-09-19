package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.utils.node.ConstantNode;
import com.company.lusnikov.nsu.lab1.utils.node.Tags;
import com.company.lusnikov.nsu.lab1.utils.data.ResolveData;
import com.company.lusnikov.nsu.lab1.utils.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Floatinfo extends TagsHandler {
    {
        tag = Tags.FLOAT;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        refs.add(new StaticData(fourByteInInt((byte)inputStream.read(), (byte)inputStream.read(),
                (byte)inputStream.read(), (byte)inputStream.read()).toString()));
        return new ConstantNode(tag, refs);
    }
}
