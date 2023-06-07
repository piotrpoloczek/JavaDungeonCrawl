package com.codecool.dungeoncrawl.logic.something;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Something implements Drawable {

    protected Cell cell;
    private String name;

    public Something(Cell cell) {
        this.cell = cell;
    }

}
