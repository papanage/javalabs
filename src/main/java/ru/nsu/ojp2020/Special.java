package ru.nsu.ojp2020;

import java.util.Arrays;

public final class Special {
    private static final int[] cpl = new int[]{203, 232, 239, 239, 242, 164, 163, 198, 242, 241, 234, 245, 228, 231, 246, 163, 252, 242, 248, 163, 235, 228, 249, 232, 163, 243, 228, 246, 246, 232, 231, 163, 247, 235, 236, 246, 163, 247, 228, 246, 238, 164};

    public Special() {
    }

    protected String getSecurityMessage() {
        return (String)Arrays.stream(cpl).mapToObj((var0) -> {
            return String.valueOf((char)(var0 - 131));
        }).reduce("", (var0, var1) -> {
            return var0 + var1;
        });
    }
}
