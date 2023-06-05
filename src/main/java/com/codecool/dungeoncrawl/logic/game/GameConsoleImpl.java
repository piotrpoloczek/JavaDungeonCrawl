package com.codecool.dungeoncrawl.logic.game;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.level.Level;
import com.codecool.dungeoncrawl.logic.level.LevelFactory;

import java.util.List;

public class GameConsoleImpl implements Game {

    private List<Level> levels;

    private Player player;

    public GameConsoleImpl(Player player, LevelFactory levelFactory) {
        this.player = player;
        this.levels = levelFactory.create();
    }

    @Override
    public void start() {

    }

    @Override
    public void refresh() {

    }
}
