package com.codecool.dungeoncrawl.logic.map;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SymbolsAndClasses {
    @Getter
    private static Map<Character, String> symbolsClasses = new HashMap<>();
    private static final String GAME_OBJECT_PACKAGE_PATH = "com.codecool.dungeoncrawl.logic.gameobject.";

    static {
        //        symbolsClasses.put('b' , GAME_OBJECT_PACKAGE_PATH + "actors.monsters.Boss");
        symbolsClasses.put('s' , GAME_OBJECT_PACKAGE_PATH + "actors.monsters.Skeleton");
        symbolsClasses.put('z' , GAME_OBJECT_PACKAGE_PATH + "actors.monsters.Ghost");
        symbolsClasses.put('m' , GAME_OBJECT_PACKAGE_PATH + "actors.monsters.Minotaur");

        symbolsClasses.put('@' , GAME_OBJECT_PACKAGE_PATH + "actors.player.Player");
        symbolsClasses.put('w' , GAME_OBJECT_PACKAGE_PATH + "actors.npc.Wizard");
        symbolsClasses.put('p' , GAME_OBJECT_PACKAGE_PATH + "actors.npc.Princes");

        symbolsClasses.put('k' , GAME_OBJECT_PACKAGE_PATH + "items.keys.RedKey");
        symbolsClasses.put('a' , GAME_OBJECT_PACKAGE_PATH + "items.food.Apple");
        symbolsClasses.put('$' , GAME_OBJECT_PACKAGE_PATH + "items.treasures.Gold");
        symbolsClasses.put('x' , GAME_OBJECT_PACKAGE_PATH + "items.treasures.Crown");

        symbolsClasses.put('g' , GAME_OBJECT_PACKAGE_PATH + "specialitems.doors.RedDoor");
        symbolsClasses.put('d' , GAME_OBJECT_PACKAGE_PATH + "specialitems.stairs.StairsDown");
        symbolsClasses.put('h' , GAME_OBJECT_PACKAGE_PATH + "specialitems.stairs.StairsHeaven");
    }
}
