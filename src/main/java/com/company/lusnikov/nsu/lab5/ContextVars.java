package com.company.lusnikov.nsu.lab5;


import lombok.Data;
import lombok.NonNull;
import org.objectweb.asm.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ContextVars {

    private Map<String, StringWithPos> strings = new HashMap<>();
    private Map<String, IntWithPos> integers = new HashMap<>();
    private List<Label> labelList = new ArrayList<>();

    private Boolean isPrint = false;
    private Boolean isDecl = false;
    private int operCount2 = 0;
    private int countVars = 0;

    @Data
    public static class StringWithPos {
        @NonNull
        private String string;
        @NonNull
        private Integer pos;
    }

    @Data
    public static class IntWithPos {
        @NonNull
        private Integer integer;
        @NonNull
        private Integer pos;
    }
}
