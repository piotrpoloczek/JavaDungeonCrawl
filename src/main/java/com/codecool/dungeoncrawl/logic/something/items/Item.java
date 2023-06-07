package com.codecool.dungeoncrawl.logic.something.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.something.Something;

public class Item extends Something {

    public Item(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }
}
