package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.utils.node.ConstantNode;
import com.company.lusnikov.nsu.lab1.utils.node.Tags;
import com.company.lusnikov.nsu.lab1.utils.data.ResolveData;
import com.company.lusnikov.nsu.lab1.utils.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Utf8 extends TagsHandler {
    {
        tag = Tags.UTF8;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        int count = twoByteInInt((byte)inputStream.read(), (byte)inputStream.read());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < count; i++){
           //stringBuilder.append((byte)inputStream.read());
           stringBuilder.appendCodePoint((byte)inputStream.read());
        }
        refs.add(new StaticData(stringBuilder.toString()));
        return new ConstantNode(tag, refs);
    }
}
