package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;

public class FullPlateArmor extends Armor{
    private static String NAME = "plate armor";
    public FullPlateArmor(Cell cell) {
        super(cell);
        this.setName(NAME);
    }
}
