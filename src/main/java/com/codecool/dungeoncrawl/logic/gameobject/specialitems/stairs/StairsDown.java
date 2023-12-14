package com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.specialitems.SpecialItem;

public class StairsDown extends SpecialItem {

    public StairsDown(Cell cell) {
        super(cell);
    }

    @Override
    public GameEvent action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            System.out.println("Congratulations you can go to the next level!!!");
            return GameEvent.NEXT_LEVEL;
        }
        return null;
    }

    @Override
    public String getTileName() {
        return "stairs down";
    }
}
