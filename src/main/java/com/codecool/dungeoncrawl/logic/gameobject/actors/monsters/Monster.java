package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Player;


public class Monster extends Actor {

    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }

    @Override
    protected void fight(Actor actor) {

    }

    @Override
    protected void action(GameObject gameObject) throws NewLevelException {

    }

    public void move(int dx, int dy, Player player) throws NewLevelException {
        Cell nextCell = null;

        if(isPlayerInSight(player)) {
            Direction direction = getChasePlayerDirection(player);
            nextCell = cell.getNeighbor(direction.getX(), direction.getY());
        }
        else {
            nextCell = cell.getNeighbor(dx, dy);
        }

        if (nextCell.getGameObject() != null) {
            GameObject gameObject = nextCell.getGameObject();
            action(gameObject);
        }
        else if (nextCell.getType().equals(CellType.FLOOR)) {
            cell.setGameObject(null);
            nextCell.setGameObject(this);
            cell = nextCell;
        }

    }

    private Direction getChasePlayerDirection(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        int differenceX = Math.abs(cell.getX() - playerX);
        int differenceY = Math.abs(cell.getY() - playerY);

        boolean chaseOnX = differenceX > differenceY;

        if(chaseOnX) {
            if(cell.getX() - playerX > 0) return Direction.LEFT;
            else {
                return Direction.RIGHT;
            }
        }
        if(cell.getY() - playerY > 0) return Direction.UP;
        else {
            return Direction.DOWN;
        }
    }

    private boolean isPlayerInSight(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        if(Math.abs(cell.getX() - playerX) < 4 && Math.abs(cell.getY() - playerY) < 4) {
            return true;
        }

        return false;
    }
}