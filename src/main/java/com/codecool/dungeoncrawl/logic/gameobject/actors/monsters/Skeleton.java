package com.codecool.dungeoncrawl.logic.gameobject.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import lombok.Getter;
import lombok.Setter;

public class Skeleton extends Monster {

    private int health = 20;

    @Getter @Setter
    private int attack = 2;

    @Getter @Setter
    private int defense = 2;

    @Getter @Setter
    private int dexterity = 2;

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(health);
    }



    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    protected void fight(Actor actor) {
        attack(actor);
    }

    @Override
    public void action(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            fight(player);
        }
    }

}
