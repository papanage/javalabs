package com.company.lusnikov.nsu.lab5;

import com.company.lusnikov.nsu.lab5.common.LabelInfo;
import gramma.IoParser;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class LabelListener extends gramma.IoBaseListener {
    @Getter
    List<LabelInfo> labelInfos = new ArrayList<>();

    @Override
    public void exitLabel(IoParser.LabelContext ctx) {
        LabelInfo labelInfo = new LabelInfo();
        labelInfo.setNumber(Integer.parseInt(ctx.INT().getText()));
        labelInfo.setParent(ctx.getParent().getParent());


        labelInfos.add(labelInfo);
        super.exitLabel(ctx);
    }

}
