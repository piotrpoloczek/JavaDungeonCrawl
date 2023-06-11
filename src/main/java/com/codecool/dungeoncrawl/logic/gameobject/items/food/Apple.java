package com.codecool.dungeoncrawl.logic.gameobject.items.food;

import com.codecool.dungeoncrawl.logic.Cell;

public class Apple extends Food {

    private static String NAME = "apple";


    public Apple(Cell cell) {
        super(cell);
        this.setName(NAME);
    }

}