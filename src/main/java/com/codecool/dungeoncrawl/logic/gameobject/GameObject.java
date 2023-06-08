package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import lombok.Getter;

public abstract class GameObject implements Drawable {

    @Getter
    protected Cell cell;
    private String name;

    public GameObject(Cell cell) {
        this.cell = cell;
        cell.setSomething(this);
    }

    public GameObject() {
    }

    public void setCell(Cell cell) {
        this.cell = cell;
        cell.setSomething(this);
    }

    public String getName() {
        return name;
    }
}
