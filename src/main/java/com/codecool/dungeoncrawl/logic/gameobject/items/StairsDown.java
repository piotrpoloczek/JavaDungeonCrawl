package com.codecool.dungeoncrawl.logic.gameobject.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class StairsDown extends Item {

    public StairsDown(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "stairs down";
    }
}
