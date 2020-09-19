package com.company.lusnikov.nsu.lab1.fab.confab;

import com.company.lusnikov.nsu.lab1.fab.FabricyHandler;
import com.company.lusnikov.nsu.lab1.tagshandlers.Class;
import com.company.lusnikov.nsu.lab1.tagshandlers.TagsHandler;

public class ClassFab implements FabricyHandler {
    @Override
    public TagsHandler fab() {
        return new Class();
    }
}
