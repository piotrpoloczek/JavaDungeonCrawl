package com.codecool.dungeoncrawl.logic.map;

import java.util.ArrayList;
import java.util.List;

public class GameMapFactory {

    public static List<String> createGameMaps() {
        ArrayList<String> gameMaps = new ArrayList<>();
        gameMaps.add("/newMap.txt");
        gameMaps.add("/newMap1.txt");
        gameMaps.add("/newMap2.txt");
        gameMaps.add("/newMap3.txt");
        return gameMaps;
    }
}
