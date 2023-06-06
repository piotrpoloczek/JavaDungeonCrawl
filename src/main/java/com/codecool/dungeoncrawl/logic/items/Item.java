package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Item {

    private String name;
    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
    }
}
