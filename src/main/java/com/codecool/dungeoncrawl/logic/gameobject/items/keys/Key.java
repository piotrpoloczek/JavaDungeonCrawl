package com.codecool.dungeoncrawl.logic.gameobject.items.keys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public void use(Player player) {
    }
}
