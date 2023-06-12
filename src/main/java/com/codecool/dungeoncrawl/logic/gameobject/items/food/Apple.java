package com.codecool.dungeoncrawl.logic.gameobject.items.food;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;

public class Apple extends Food {

    private static String NAME = "apple";
    private static int itemPower = 5;


    public Apple(Cell cell) {
        super(cell, itemPower);
        this.setName(NAME);
    }
}