package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Skeleton;
import com.codecool.dungeoncrawl.logic.gameobject.actors.npc.Wizard;
import com.codecool.dungeoncrawl.logic.gameobject.items.food.Apple;
import com.codecool.dungeoncrawl.logic.gameobject.items.keys.Key;
import com.codecool.dungeoncrawl.logic.gameobject.items.keys.RedKey;
import com.codecool.dungeoncrawl.logic.gameobject.specialitems.doors.RedDoor;
import com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs.StairsDown;
import com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Gold;
import com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs.StairsHeaven;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MapLoader {

    private static Map<Character, String> symbolsClasses = new HashMap<>();

    static {
        symbolsClasses.put('s' , "com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Skeleton");
        symbolsClasses.put('@' , "com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player");
        symbolsClasses.put('d' , "com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs.StairsDown");
        symbolsClasses.put('w' , "com.codecool.dungeoncrawl.logic.gameobject.actors.npc.Wizard");
        symbolsClasses.put('k' , "com.codecool.dungeoncrawl.logic.gameobject.items.keys.RedKey");
        symbolsClasses.put('g' , "com.codecool.dungeoncrawl.logic.gameobject.specialitems.doors.RedDoor");
        symbolsClasses.put('$' , "com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Gold");
        symbolsClasses.put('h' , "com.codecool.dungeoncrawl.logic.gameobject.specialitems.stairs.StairsHeaven");
    }
    public static GameMap loadMap(String filename, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream(filename);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(player, width, height, CellType.EMPTY);
        List<Monster> monsters = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    char currentChar = line.charAt(x);
                    if(currentChar == ' ') {
                        cell.setType(CellType.EMPTY);
                    }
                    else if(currentChar == '#') {
                        cell.setType(CellType.WALL);
                    }
                    else {
                        try {
                            cell.setType(CellType.FLOOR);
                            if(symbolsClasses.get(currentChar) == null) {
                                continue;
                            }
                            String className = symbolsClasses.get(currentChar);

                            Class aClass = Class.forName(className);

                            Class[] types = {Cell.class};
                            Constructor constructor = aClass.getConstructor(types);

                            Object[] parameters = {cell};
                            Object o = constructor.newInstance(parameters);

                            if(currentChar == 's') {
                                monsters.add((Monster) o);
                            }
                            if(currentChar == '@') {
                                map.getPlayer().setCell(cell);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        map.setMonsters(monsters);

        return map;
    }

}
