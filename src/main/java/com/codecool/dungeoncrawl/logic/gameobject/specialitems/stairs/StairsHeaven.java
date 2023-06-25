package com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.exceptions.GameEndException;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.specialitems.SpecialItem;

public class StairsHeaven extends SpecialItem {

    private static String NAME = "stairs heaven";


    public StairsHeaven(Cell cell) {
        super(cell);
        setName(NAME);
    }

    @Override
    public GameEvent action(GameObject gameObject) throws GameException {
        if (gameObject instanceof Player) {
            return GameEvent.GAME_END;
        }
        return null;
    }
}
