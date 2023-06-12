package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class LeatherArmor extends Armor{
    private static String NAME = "leather armor";
    public LeatherArmor(Cell cell) {
        super(cell);
        this.setName(NAME);
    }

    @Override
    public void use(Player player) {

    }
}
