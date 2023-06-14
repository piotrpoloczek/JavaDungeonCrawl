package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.*;

public class MapLoader {

    private static Map<Character, String> symbolsClasses = new HashMap<>();
    private static final String GAME_OBJECT_PACKAGE_PATH = "com.codecool.dungeoncrawl.logic.gameobject.";

    static {
        symbolsClasses.put('s' , GAME_OBJECT_PACKAGE_PATH + "actors.monsters.Skeleton");
        symbolsClasses.put('@' , GAME_OBJECT_PACKAGE_PATH + "actors.player.Player");
        symbolsClasses.put('d' , GAME_OBJECT_PACKAGE_PATH + "specialitems.stairs.StairsDown");
        symbolsClasses.put('w' , GAME_OBJECT_PACKAGE_PATH + "actors.npc.Wizard");
        symbolsClasses.put('k' , GAME_OBJECT_PACKAGE_PATH + "items.keys.RedKey");
        symbolsClasses.put('g' , GAME_OBJECT_PACKAGE_PATH + "specialitems.doors.RedDoor");
        symbolsClasses.put('$' , GAME_OBJECT_PACKAGE_PATH + "items.treasures.Gold");
        symbolsClasses.put('h' , GAME_OBJECT_PACKAGE_PATH + "specialitems.stairs.StairsHeaven");
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
                    setCellTypeByChar(currentChar, cell);

                    if (currentChar != ' ' && currentChar != '#') {
                        if(symbolsClasses.get(currentChar) == null) {
                            continue;
                        }
                        String className = symbolsClasses.get(currentChar);
                        Object o = createObjectByClassName(className, cell);

                        if(currentChar == 's') {
                            monsters.add((Monster) o);
                        }
                        if(currentChar == '@') {
                            map.getPlayer().setCell(cell);
                        }
                    }
                }
            }
        }

        map.setMonsters(monsters);

        return map;
    }

    private static Object createObjectByClassName(String className, Cell cell) {

        try {
            Class aClass = Class.forName(className);

            Class[] types = {Cell.class};
            Constructor constructor = aClass.getConstructor(types);

            Object[] parameters = {cell};
            return constructor.newInstance(parameters);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setCellTypeByChar(char symbol, Cell cell) {
        if(symbol == ' ') {
            cell.setType(CellType.EMPTY);
        }
        else if(symbol == '#') {
            cell.setType(CellType.WALL);
        }
        else {
            cell.setType(CellType.FLOOR);
        }
    }

}
