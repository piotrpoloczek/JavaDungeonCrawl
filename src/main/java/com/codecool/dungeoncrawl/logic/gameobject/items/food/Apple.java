package com.codecool.dungeoncrawl.logic.gameobject.items.food;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

public class Apple extends Item {


    public Apple(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "apple";
    }
}