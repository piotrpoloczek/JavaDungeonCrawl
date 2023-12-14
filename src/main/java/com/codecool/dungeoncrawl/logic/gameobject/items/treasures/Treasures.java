package com.codecool.dungeoncrawl.logic.gameobject.items.treasures;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;
import lombok.Setter;

public abstract class Treasures  extends Item {

    @Getter @Setter
    private int value;

    public Treasures(Cell cell, int value) {
        super(cell);
        this.value = value;
    }


    @Override
    public void use(Player player) {
    }
}
