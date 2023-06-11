package com.codecool.dungeoncrawl.logic.gameobject.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public abstract class Item extends GameObject {

    public Item(Cell cell) {
        super(cell);
    }

    public void putInInventory(Player player) {
        player.getInventory().putItemToInventory(this);
    }

    @Override
    public void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            this.putInInventory(player);

            this.getCell().setGameObject(null);
            this.setCellToNull();
        }
    }

    @Override
    public String getTileName() {
        return this.getName();
    }

}
