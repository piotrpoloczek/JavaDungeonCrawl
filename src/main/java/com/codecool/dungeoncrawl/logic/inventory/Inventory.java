package com.codecool.dungeoncrawl.logic.inventory;


import java.util.ArrayList;
import java.util.List;

import com.codecool.dungeoncrawl.logic.gameobject.items.Gold;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;
import lombok.Setter;


public class Inventory {
    private List<Item> sack;

    private int inventorySize = 2;

    private List<Item> gold;

    @Getter
    @Setter
    private Item currentWepon;

    @Getter
    @Setter
    private Item currentArmor;

    //TODO ? limit space in sack

    public Inventory() {
        this.sack = new ArrayList<>();
        this.gold = new ArrayList<>();
    }

    public void putItemToInventory(Item item){
        if (isGold(item)){
            gold.add(item);
        } else if (!isInventoryFull()){
            sack.add(item);
        } else {
            System.out.println("Inventory is full!");
        }
    }

    //TODO geter do zawracania ilości złota (wielkości listy)

    private boolean isInventoryFull() {
        return sack.size()>=inventorySize;
    }

    private boolean isGold(Item item){
        return item instanceof Gold;
    }
}