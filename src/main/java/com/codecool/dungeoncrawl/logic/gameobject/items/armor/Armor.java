package com.codecool.dungeoncrawl.logic.gameobject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.UseItem;
import lombok.Getter;
import lombok.Setter;

public abstract class Armor extends Item implements UseItem {

    @Getter @Setter
    private int power;

    public Armor(Cell cell) {
        super(cell);
    }

    public void use(Player player) {
        player.setDefense(player.getDefense()+this.power);
    }
}
