package com.codecool.dungeoncrawl.logic.gameobject.items.treasures;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;
import lombok.Setter;

public class Crown extends Item {

    @Setter
    private static String NAME = "crown";

    private int value;
    public Crown(Cell cell) {
        super(cell);
        this.setName(NAME);
    }

    @Override
    public void use(Player player) {

    }
}
