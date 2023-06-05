package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.List;

public class Game {

    private List<Level> levels;

    private Player player;

    public Game(Player player) {
        this.player = player;
    }

}
