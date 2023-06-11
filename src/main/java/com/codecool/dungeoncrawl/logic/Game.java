package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.map.GameMapFactory;
import com.codecool.dungeoncrawl.logic.map.MapLoader;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ConcurrentModificationException;
import java.util.List;

import static com.codecool.dungeoncrawl.logic.Direction.generateRandomDirection;

public class Game {

    private List<String> mapFiles;
    @Getter @Setter
    private GameMap currentMap;
    private int actualLevel;
    private Player player;


    public Game() {
        this.player = new Player();
        this.mapFiles = GameMapFactory.createGameMaps();
        this.actualLevel = 0;
        this.currentMap = MapLoader.loadMap(mapFiles.get(actualLevel), player);
    }

    private void monstersTurn() throws NewLevelException {
        for (Monster monster : getCurrentMap().getMonsters()) {

            if (!monster.isAlive()) {
                getCurrentMap().removeMonsters(monster);
            } else {
                Direction direction = generateRandomDirection();
                System.out.println("x: " + direction.getX() + " y: " +direction.getY());
                monster.move(direction.getX(), direction.getY(), getCurrentMap().getPlayer());
            }
        }
    }

    public void gameTurn(Direction direction) {
        try {
            System.out.println(direction.getX() + " : " + direction.getY());
            getCurrentMap().getPlayer().getName();
            getCurrentMap().getPlayer().move(direction.getX(), direction.getY());
            monstersTurn();
        } catch (NewLevelException e) {
            System.out.println(e);
            changeCurrentMap();
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
    }

    private void changeCurrentMap() {
        actualLevel++;
        currentMap = MapLoader.loadMap(mapFiles.get(actualLevel), player);
    }
}
