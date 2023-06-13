package com.codecool.dungeoncrawl.logic.gameobject.actors.attributes;

import org.w3c.dom.Attr;

import java.util.concurrent.atomic.AtomicReference;

public class Health extends Attribute {

    private int actualHealth;
    private int maxHealth;
    private String name;


    public Health(int health) {
        this.maxHealth = health;
        restoreHealthLevel();
    }

    public void restoreHealthLevel() {
        this.actualHealth = this.maxHealth;
    }

    public boolean healthBelowZero() {
        return this.actualHealth < 0;
    }

}
