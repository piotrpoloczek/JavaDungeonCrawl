package com.codecool.dungeoncrawl.logic.gameobject.actors.player;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.classes.Class;
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
    @Getter @Setter
    private Class playerClass;
    private Level playerLevel;
    private static int PLAYER_HEALTH = 100;
    private static int PLAYER_DEFENSE = 0;

    public Player(Cell cell) {
        super(cell, PLAYER_HEALTH, PLAYER_DEFENSE);
        this.inventory = new Inventory();
        this.playerLevel = new Level(this);
        this.experience = 0;
    }

    public Player(){
        super(PLAYER_HEALTH, PLAYER_DEFENSE);
        setAttack(5);
//        setDefense(PLAYER_DEFENSE);
        setDexterity(5);
        this.inventory = new Inventory();
        this.playerLevel = new Level(this);
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
            System.out.println("Exp gained " + monster.getExpReward());

            if(playerLevel.isNewLevel(getExperience())) {
                playerLevel.levelUp();
            }
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
        item.use(this);
    }

    @Override
    public String getTileName() {
        return this.playerClass.getTileName();
    }
}