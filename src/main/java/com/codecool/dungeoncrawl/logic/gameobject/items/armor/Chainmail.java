package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.food.Food;

public class Chainmail extends Armor{
    private static int itemPower = 10;
    private static String NAME = "chainmail";
    public Chainmail(Cell cell) {
        super(cell, itemPower);
        this.setName(NAME);
    }

    }

