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

    private ContextVars parent;
    private Map<String, StringWithPos> strings = new HashMap<>();
    private Map<String, IntWithPos> integers = new HashMap<>();
    List<Label> labelList = new ArrayList<>();

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
