package com.codecool.dungeoncrawl.logic.gameobject.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.UseItem;
import lombok.Getter;
import lombok.Setter;

public abstract class Weapon extends Item implements UseItem {

    @Getter @Setter
    private int power;

    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public void use(Player player) {
        player.setAttack(player.getAttack() + this.power );
        player.getInventory().setCurrentWeapon(this);
    }
}
