package com.codecool.dungeoncrawl.logic.gameobject.items.keys;

import com.codecool.dungeoncrawl.logic.Cell;

public class RedKey extends Key{


    private static String NAME = "key";

    public RedKey(Cell cell) {
        super(cell);
        this.setName(NAME);
    }
}

