package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;

public class LeatherArmor extends Armor{
    private static String NAME = "leather armor";
    public LeatherArmor(Cell cell) {
        super(cell);
        this.setName(NAME);
    }
}
