package com.codecool.dungeoncrawl.logic.gameobject.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.messages.Message;

public abstract class Item extends GameObject implements UseItem {

    public Item(Cell cell) {
        super(cell);
    }

    public void putInInventory(Player player) {
        player.getInventory().putItemToInventory(this);
    }
//    public void removeFromPlayerInventory(Player player) {
//        player.getInventory().removeFromInventory(this);
//    }

    @Override
    public GameEvent action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            this.putInInventory(player);

            Message.getInstance().setActualMessage("You found the: " + this.getName());
            this.getCell().setGameObject(null);
            this.setCellToNull();
        }
        return null;
    }

    @Override
    public String getTileName() {
        return this.getName();
    }

}
