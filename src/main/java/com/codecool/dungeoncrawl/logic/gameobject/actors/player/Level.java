package com.codecool.dungeoncrawl.logic.gameobject.actors.player;

import lombok.Getter;
import lombok.Setter;

public class Level {

    private Player player;
    private int newLevelExperience;
    @Getter
    private int level;


    public Level(Player player) {
        this.player = player;
        this.level = 1;
        newLevelExperience = 100;
    }

    public void levelUp() {
        System.out.println("New level achieved! " + getLevel() + "!");
        player.setHealth(player.getHealth() + 10);
        player.setAttack(player.getHealth() + 5);
        player.setDefense(player.getHealth() + 3);
        player.setDexterity(player.getHealth() + 2);
    }

    private int calculateNewLevelExperience() {
        return (int) (player.getExperience() * 1.1);
    }

    public boolean isNewLevel(int actualExperience) {
        if(actualExperience >= newLevelExperience) {
            newLevelExperience = calculateNewLevelExperience();
            level++;
            return true;
        }
        return false;
    }
}