package com.codecool.dungeoncrawl.logic.gameobject.actors.player;

import lombok.Getter;
import lombok.Setter;

public class Level {

    private Player player;
    @Getter @Setter
    private int actualExperience;
    private int newLevelExperience;


    public Level(Player player) {
        // base level
    }


    private void LevelUp() {
        player.setHealth(player.getHealth() + 10);
        // itd
    }

    private int calculateNewLevelExperience() {
        return (int) ((int) getActualExperience() * 1.1);
    }


}
