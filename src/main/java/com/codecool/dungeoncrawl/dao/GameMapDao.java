package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.model.GameState;

import java.io.IOException;
import java.util.List;

public interface GameMapDao {

    void add(GameMap gameMap) throws IOException;
    void update(GameMap gameMap);
    GameMap get(int id);
    List<GameMap> getAll();
}
