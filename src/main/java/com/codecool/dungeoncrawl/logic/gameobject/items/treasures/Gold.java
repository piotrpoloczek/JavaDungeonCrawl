package com.codecool.dungeoncrawl.logic.gameobject.items.treasures;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

public class Gold extends Item {

    private int value;
    public Gold(Cell cell) {
        super(cell);
        this.value = RandomGenerator.getRandomInt(5);
    }

    @Override
    public String getTileName() {
        return "gold";
    }
}