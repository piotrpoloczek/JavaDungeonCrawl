package com.codecool.dungeoncrawl.logic.level;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.List;

public class Level {

    private GameMap map;
    private Player player;
    private List<Actor> opponents;


    public Level(GameMap map, Player player) {
        this.map = map;
        this.player = player;
    }

    private void turn() {
        playerTurn();
        opponentsTurn();
    }

    private void playerTurn() {

    }

    private void opponentsTurn() {
        for (Actor opponent : opponents) {
            opponentTurn(opponent);
        }
    }

    private void opponentTurn(Actor opponent) {
        // perform opponent actions
    }

}
