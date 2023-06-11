package com.codecool.dungeoncrawl.logic.gameobject.actors.player;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;
import lombok.Setter;

public class Player extends Actor {

    @Getter @Setter
    Inventory inventory;

    private static int PLAYER_HEALTH = 50;

    public Player(Cell cell) {
        super(cell, PLAYER_HEALTH);
        this.inventory = new Inventory();
    }

    public Player(){
        super(PLAYER_HEALTH);
        this.inventory = new Inventory();
    }

    public String getName() {
        return "Piotr";
    }


    @Override
    protected void fight(Actor actor) {
        this.attack(actor);
    }

    @Override
    public void action(GameObject gameObject) throws GameException {

        // if the object is monster then player will attack it,
        // in other situation the gameObject will take action, taking player as parameter

        if (gameObject instanceof Monster) {
            Monster monster = (Monster) gameObject;
            fight(monster);
        } else {
            gameObject.action(this);
        }
    }

    public void useItem(Item item) {
//        item.useIt(this);
        //use item;
    }

    public String getTileName() {
        return "player";
    }
}
