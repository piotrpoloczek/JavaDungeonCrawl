package com.codecool.dungeoncrawl.logic.map;

import java.util.ArrayList;
import java.util.List;

public class GameMapFactory {

    public static List<String> createGameMaps() {
        ArrayList<String> gameMaps = new ArrayList<>();
        gameMaps.add("/map.txt");
        gameMaps.add("/map2.txt");
        return gameMaps;
    }
}