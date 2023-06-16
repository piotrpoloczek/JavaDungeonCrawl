package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class Chainmail extends Armor{
    private static int POWER = 10;
    private static String NAME = "chainmail";
    public Chainmail(Cell cell) {
        super(cell);
        this.setName(NAME);
        this.setPower(POWER);
    }

    @Override
    public void use(Player player) {

    }
}
