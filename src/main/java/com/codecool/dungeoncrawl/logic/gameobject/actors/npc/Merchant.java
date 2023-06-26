package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

public class Merchant extends Npc {


    public Merchant(int health) {
        super(health);
    }

    public Merchant(Cell cell) {
        super(cell);
    }

    public Merchant(Cell cell, int health) {
        super(cell, health);
    }

    @Override
    public GameEvent action(GameObject gameObject) throws NewLevelException {

        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;

            System.out.println(player.getName() + " you are lucky because you meet the best merchant in the world.");
            System.out.println("What do you need to buy?");
        }

        return null;
    }

    @Override
    public String getTileName() {
        return "merchant";
    }
}
