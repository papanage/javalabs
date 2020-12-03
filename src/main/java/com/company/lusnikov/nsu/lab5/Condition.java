package com.company.lusnikov.nsu.lab5;

import lombok.Data;

@Data
public class Condition {
    private Condition not;
    private String var1;
    private String var2;

}
