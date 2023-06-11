package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;


public class Wizard extends Npc {

    public Wizard(Cell cell) {
        super(cell);
    }

    @Override
    public void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            System.out.println("You met an old wizard");
            System.out.println("He won't let you go unless you bring him 50 gold");
            if(player.getInventory().getGoldAmount() >= 50) {
                System.out.println("Wizard kindly lets you in...");
                this.getCell().setGameObject(null);
            }
            else {
                System.out.println("Bring more gold!");
            }
        }
    }

    @Override
    public String getTileName() {
        return "wizard";
    }
}
