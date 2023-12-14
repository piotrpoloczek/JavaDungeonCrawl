package com.codecool.dungeoncrawl.logic.tasks;

import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {
    @Getter
    private String name;
    private List<Item> reward;
    @Getter
    private int expReward;
    private String aim;
    private boolean finished;

    public Task(String name, String aim, int expReward) {
        this.name = name;
        this.aim = aim;
        this.expReward = expReward;
    }
}
