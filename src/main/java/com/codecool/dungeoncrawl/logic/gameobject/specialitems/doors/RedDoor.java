package com.codecool.dungeoncrawl.logic.gameobject.specialitems.doors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.messages.Message;

public class RedDoor extends Door {

    public RedDoor(Cell cell) {
        super(cell);
    }

    @Override
    public GameEvent action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            System.out.println("Solid door");
            System.out.println("You need a key");

            Item key = player.getInventory().getSack().stream()
                    .filter(item1 -> "redKey".equals(item1.getName()))
                    .findAny()
                    .orElse(null);


            if(player.getInventory().isInABag(key)) {
                this.getCell().setGameObject(null);
//                Item key = player.getInventory().getSack().stream()
//                        .filter(item1 -> "RedKey".equals(item1.getName()))
//                        .findAny()
//                        .orElse(null);
                player.getInventory().removeFromInventory(key);

            }
            else {
                Message.getInstance().setActualMessage("You don't have a key");
                System.out.println("You don't have a key");
            }
        }

        return null;
    }

    @Override
    public String getTileName() {
        return "door";
    }

    @Override
    public void use(Player player) {

    }
}
