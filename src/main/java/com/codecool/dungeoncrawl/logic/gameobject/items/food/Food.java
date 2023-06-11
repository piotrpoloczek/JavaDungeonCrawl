package com.codecool.dungeoncrawl.logic.gameobject.items.food;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;

public abstract class Food extends Item {
    @Getter
    private int itemPower;

    public Food(Cell cell, int itemPower) {
        super(cell);
        this.itemPower = itemPower;
    }

}
