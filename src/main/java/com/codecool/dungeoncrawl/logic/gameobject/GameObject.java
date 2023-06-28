package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameEvent;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

public abstract class GameObject implements Drawable {

    @JsonBackReference
    @Getter
    protected Cell cell;
    @Getter @Setter
    private String name;

    public GameObject(Cell cell) {
        this.cell = cell;
        cell.setGameObject(this);
    }

    public GameObject() {
    }

    public abstract GameEvent action(GameObject gameObject) throws GameException;

    public void setCell(Cell cell) {
        this.cell = cell;
        cell.setGameObject(this);
    }

    public void setCellToNull(){
        this.cell = null;
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public String getTileName() {
        return this.getName();
    }
}
