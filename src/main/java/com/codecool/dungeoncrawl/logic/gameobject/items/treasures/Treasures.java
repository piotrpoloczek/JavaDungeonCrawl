package com.codecool.dungeoncrawl.logic.gameobject.items.treasures;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;

public class Treasures  extends Item {

    @Getter
    private int value;

    public Treasures(Cell cell, int value) {
        super(cell);
        this.value = value;
    }


    @Override
    public void use(Player player) {

    }
}
