package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

import java.util.Random;


public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);


    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public static Direction generateRandomDirection() {
        switch (RandomGenerator.getRandomIntBetween(1, 4)) {
            case 1:
                return Direction.UP;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
            default:
                return Direction.RIGHT;
        }
    }
}

