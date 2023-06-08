package com.codecool.dungeoncrawl.logic.gameobject.items.special;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

public class StairsDown extends Item {

    public StairsDown(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "stairs down";
    }
}
