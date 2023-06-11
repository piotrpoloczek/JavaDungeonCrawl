package com.codecool.dungeoncrawl.logic.gameobject.specialitems.doors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

public abstract class Door extends Item {

    public Door(Cell cell) {
        super(cell);
    }
}
