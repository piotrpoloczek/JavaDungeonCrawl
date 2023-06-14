package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.tasks.Journal;
import com.codecool.dungeoncrawl.logic.tasks.Task;


public class Princes extends Npc {

    public Princes(Cell cell) {
        super(cell);
    }

    @Override
    public void action(GameObject gameObject) throws GameException {

    }

    @Override
    public String getTileName() {
        return "princes";
    }
}
