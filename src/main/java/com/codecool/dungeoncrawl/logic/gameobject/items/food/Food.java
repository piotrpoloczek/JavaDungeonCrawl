package com.codecool.dungeoncrawl.logic.gameobject.items.food;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.UseItem;
import lombok.Getter;

public abstract class Food extends Item implements UseItem {

    @Getter
    private int itemPower;

    public Food(Cell cell, int itemPower) {
        super(cell);
        this.itemPower = itemPower;
    }

    public void use(Player player) {
        player.setHealth(player.getHealth() + itemPower);
    }

}
