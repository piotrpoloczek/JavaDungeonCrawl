package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Ghost;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Minotaur;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Skeleton;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * To add new object which IS NOT a monster:
 * Go to SymbolsAndClasses and add new line to static block. You need to provide new symbol, and fully qualified class name.
 * Path up to gameobject package is in GAME_OBJECT_PACKAGE_PATH as in other existing lines.
 *
 * To add new object which IS a monster:
 * Do everything as above and in method getMonstersChars() add new line with a monster
 *
 */

public class MapLoader {

    private static final Map<Character, String> symbolsClasses = SymbolsAndClasses.getSymbolsClasses();

    public static GameMap loadMap(String filename, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream(filename);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(player, width, height, CellType.EMPTY);
        List<Monster> monsters = new ArrayList<>();
        List<Character> monstersChars = getMonstersChars();

        for (int y = 0; y < height; y++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (int x = 0; x < width; x++) {
                    if (x < line.length()) {

                        Cell cell = map.getCell(x, y);
                        char currentChar = line.charAt(x);
                        setCellTypeByChar(currentChar, cell);

                        if (currentChar != ' ' && currentChar != '#') {
                            if (symbolsClasses.get(currentChar) == null) {
                                continue;
                            }
                            String className = symbolsClasses.get(currentChar);
                            Object o = createObjectByClassName(className, cell);

                            if (monstersChars.contains(currentChar)) {
                                monsters.add((Monster) o);
                            }
                            if (currentChar == '@') {
                                map.getPlayer().setCell(cell);
                            }
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

    private static List<Character> getMonstersChars() {
        List<Character> monstersChars = new ArrayList<>();
        monstersChars.add('s'); //Skeleton
        monstersChars.add('z'); //Ghost
        monstersChars.add('m'); //Minotaur

        return monstersChars;
    }

}