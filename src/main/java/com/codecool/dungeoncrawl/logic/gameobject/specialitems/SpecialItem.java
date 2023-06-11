package com.codecool.dungeoncrawl.logic.gameobject.specialitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

public abstract class SpecialItem extends GameObject {

    public SpecialItem(Cell cell) {
        super(cell);
    }
}
