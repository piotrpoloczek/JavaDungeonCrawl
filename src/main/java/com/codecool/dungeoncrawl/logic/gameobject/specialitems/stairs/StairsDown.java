package com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

public class StairsDown extends Item {

    public StairsDown(Cell cell) {
        super(cell);
    }

    @Override
    public void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            System.out.println("Congratulations you can go to the next level!!!");
            throw new NewLevelException();
        }
    }

    @Override
    public String getTileName() {
        return "stairs down";
    }
}
