package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class FullPlateArmor extends Armor{
    private static String NAME = "plate armor";
    public FullPlateArmor(Cell cell) {
        super(cell);
        this.setName(NAME);
    }

    @Override
    public void use(Player player) {

    }
}
