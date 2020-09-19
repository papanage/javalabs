package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.ConstantNode;
import com.company.lusnikov.nsu.lab1.Tags;
import com.company.lusnikov.nsu.lab1.utils.ResolveData;
import com.company.lusnikov.nsu.lab1.utils.StaticData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class Integerinfo extends TagsHandler {
    {
        tag = Tags.INTEGER;
    }
    @Override
    public ConstantNode compute(FileInputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        refs.add(new StaticData(fourByteInInt((byte)inputStream.read(), (byte)inputStream.read(),
                (byte)inputStream.read(), (byte)inputStream.read()).toString()));
        return new ConstantNode(tag, refs);
    }
}
