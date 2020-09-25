package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.common.node.ConstantNode;
import com.company.lusnikov.nsu.lab1.common.node.Tags;
import com.company.lusnikov.nsu.lab1.common.data.Ref;
import com.company.lusnikov.nsu.lab1.common.data.ResolveData;
import com.company.lusnikov.nsu.lab1.common.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Fieldref extends TagsHandler {
    {
        tag = Tags.FIELDREF;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        refs.add(new Ref(twoByteInInt((byte)inputStream.read(), (byte)inputStream.read()).toString()));
        refs.add(new StaticData("."));
        refs.add(new Ref(twoByteInInt((byte)inputStream.read(), (byte)inputStream.read()).toString()));
        return new ConstantNode(tag, refs);
    }

}
