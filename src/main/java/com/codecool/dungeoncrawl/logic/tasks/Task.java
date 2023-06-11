package com.codecool.dungeoncrawl.logic.tasks;

import com.codecool.dungeoncrawl.logic.gameobject.items.Item;

import java.util.List;

public abstract class Task {
    private String name;
    private List<Item> reward;
    private String aim;
    private boolean finished;
}
