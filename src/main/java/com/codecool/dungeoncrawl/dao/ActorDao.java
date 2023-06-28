package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;

import java.util.List;

public interface ActorDao {
    void add(Actor actor);
    void update(Actor actor);
    Actor get(int id);
    List<Actor> getAll();
}
