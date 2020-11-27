package com.company.lusnikov.nsu.lab5.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class LabelInfo {
    @NonNull
    Integer number;
    @NonNull
    ParseTree parent;
    Integer childNumber;

}
