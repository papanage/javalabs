package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.ConstantNode;
import com.company.lusnikov.nsu.lab1.Tags;
import com.company.lusnikov.nsu.lab1.utils.Ref;
import com.company.lusnikov.nsu.lab1.utils.ResolveData;
import com.company.lusnikov.nsu.lab1.utils.StaticData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class MethodHandle extends TagsHandler {
    {
        tag = Tags.METHODHANDLE;
    }
    @Override
    public ConstantNode compute(FileInputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        refs.add(new StaticData(String.valueOf((byte)inputStream.read())));
        refs.add(new StaticData(":"));
        refs.add(new Ref(twoByteInInt((byte)inputStream.read(), (byte)inputStream.read()).toString()));
        return new ConstantNode(tag, refs);
    }
}
