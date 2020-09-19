package com.company.lusnikov.nsu.lab1.tagshandlers;

import com.company.lusnikov.nsu.lab1.ConstantNode;
import com.company.lusnikov.nsu.lab1.Tags;
import com.company.lusnikov.nsu.lab1.utils.ResolveData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public abstract class TagsHandler  {
        public Tags tag;
        public abstract ConstantNode compute(FileInputStream inputStream) throws IOException;

        protected Integer twoByteInInt(byte b1, byte b2){
                return ((0xFF & b1) << 8) | (0xFF & b2);
        }

        protected Integer fourByteInInt(byte b1, byte b2, byte b3, byte b4){
                return  ((0xFF & b1) << 24) | ((0xFF & b2) << 16) |
                        ((0xFF & b3) << 8) | (0xFF & b4);
        }
}
