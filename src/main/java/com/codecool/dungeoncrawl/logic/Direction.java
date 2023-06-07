package com.codecool.dungeoncrawl.logic;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);


    private int x;
    private int y;

    Direction(int x, int y) {}

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }
}

