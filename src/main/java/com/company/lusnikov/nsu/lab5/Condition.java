package com.company.lusnikov.nsu.lab5;

import lombok.Builder;

@Builder
public class Condition {
    private Condition not;
    private Integer i1;
    private Integer i2;

}
