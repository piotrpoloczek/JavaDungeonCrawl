package com.codecool.dungeoncrawl.logic.gameobject.actors.player;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Gold;
import com.codecool.dungeoncrawl.logic.tasks.Journal;
import lombok.Getter;
import lombok.Setter;

public class Player extends Actor {

    @Getter @Setter
    private Inventory inventory;
    @Getter
    private Journal journal = new Journal();
    @Getter @Setter
    private int experience;
    private int level;

    private static int PLAYER_HEALTH = 100;

    public Player(Cell cell) {
        super(cell, PLAYER_HEALTH);
        this.inventory = new Inventory();
    }

    public Player(){
        super(PLAYER_HEALTH);
        setAttack(5);
        setDefense(5);
        setDexterity(5);
        this.inventory = new Inventory();
    }

    public String getName() {
        return "Piotr";
    }


    @Override
    protected void fight(Actor actor) {
        this.attack(actor);

        Monster monster = (Monster) actor;
        if(!actor.isAlive()) {
            this.setExperience(getExperience() + monster.getExpReward());
        }
    }

    @Override
    public void action(GameObject gameObject) throws GameException {

        // if the object is monster then player will attack it,
        // in other situation the gameObject will take action, taking player as parameter

        if (gameObject instanceof Monster) {
            Monster monster = (Monster) gameObject;
            fight(monster);
        }
        else if (gameObject instanceof Gold) {
            Gold gold = (Gold) gameObject;
            System.out.println("Collected " + ((Gold) gameObject).getValue() + " gold");
            gold.action(this);
        }
        else {
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
