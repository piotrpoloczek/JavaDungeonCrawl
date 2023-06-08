package com.codecool.dungeoncrawl.logic.gameobject.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Apple extends Item{


    public Apple(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "apple";
    }
}