package com.company.lusnikov.nsu.lab1.classreader;

import com.company.lusnikov.nsu.lab1.common.node.ConstantNode;

import java.io.InputStream;
import java.util.Vector;

public class ClassReader {
    public Vector<ConstantNode> read(InputStream in) throws Exception{
        if (readHandle(in))
         return HandleTagsController.compute(in);
        throw new Exception();
    }

    /**
     *
     * @param inputStream откуда считываем
     * @return true, если успешно считали и обработали заголовки - пока замокано, false - иначе
     * @throws Exception просто везде прокидываем, что сильно не думать
     */
    private boolean readHandle(InputStream inputStream) throws Exception{
        for (int i = 0; i < 8; i++ ){
            inputStream.read();
        }
        return true;
    }
}
