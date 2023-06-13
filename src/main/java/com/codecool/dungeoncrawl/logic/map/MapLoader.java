package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.actors.npc.Princes;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
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
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            monsters.add(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.getPlayer().setCell(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new StairsDown(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Apple(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Wizard(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Princes(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new RedKey(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new RedDoor(cell);
                            break;
                        case '$':
                            cell.setType(CellType.FLOOR);
                            new Gold(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new StairsHeaven(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }

        map.setMonsters(monsters);

        return map;
    }

}
