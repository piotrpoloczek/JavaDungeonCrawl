package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;

public class Chainmail extends Armor{
    private static String NAME = "chainmail";
    public Chainmail(Cell cell) {
        super(cell);
        this.setName(NAME);
    }
}
