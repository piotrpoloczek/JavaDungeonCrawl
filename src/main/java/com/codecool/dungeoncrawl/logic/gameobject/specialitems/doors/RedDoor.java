package com.codecool.dungeoncrawl.logic.gameobject.specialitems.doors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class RedDoor extends Door {

    public RedDoor(Cell cell) {
        super(cell);
    }

    @Override
    public void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            System.out.println("Solid door");
            System.out.println("You need a key");

            if(player.getInventory().isInABag("RedKey")) {
                this.getCell().setGameObject(null);
                player.getInventory().removeFromBag("RedKey");
            }
            else {
                System.out.println("You don't have a key");
            }
        }
    }

    @Override
    public String getTileName() {
        return "door";
    }

    @Override
    public void use(Player player) {

    }
}
