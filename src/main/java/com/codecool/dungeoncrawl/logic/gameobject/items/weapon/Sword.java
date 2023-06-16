package com.codecool.dungeoncrawl.logic.gameobject.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Weapon{

    private static int POWER = 10;


    public Sword(Cell cell) {
        super(cell);
        this.setPower(POWER);
    }
}
