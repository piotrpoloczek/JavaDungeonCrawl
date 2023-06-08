package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
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
//
//    @Override
//    public void move(int dx, int dy) throws NewLevelException {
//    }

    public void move(int dx, int dy, Player player) throws NewLevelException {
        if(isPlayerInSight(player)) {
            chasePlayer(player);
        }
        else {
            Cell nextCell = cell.getNeighbor(dx, dy);

            if (nextCell.getGameObject() != null) {
                GameObject gameObject = nextCell.getGameObject();
                action(gameObject);
            } else if (nextCell.getType().equals(CellType.FLOOR)) {
                cell.setGameObject(null);
                nextCell.setGameObject(this);
                cell = nextCell;
            }
        }

    }

    //logic to chase player
    private void chasePlayer(Player player) {

    }

    private boolean isPlayerInSight(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        if(Math.abs(cell.getX() - playerX) <= 4 && Math.abs(cell.getY() - playerY) <= 4) {
            return true;
        }

        return false;
    }
}