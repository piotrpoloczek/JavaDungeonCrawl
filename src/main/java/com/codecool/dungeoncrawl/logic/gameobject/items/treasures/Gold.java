package com.codecool.dungeoncrawl.logic.gameobject.items.treasures;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

public class Gold extends Treasures {

    private static String NAME = "gold";

    private int value;
    public Gold(Cell cell) {
        super(cell, RandomGenerator.getRandomInt(20));
        this.setName(NAME);
    }
}