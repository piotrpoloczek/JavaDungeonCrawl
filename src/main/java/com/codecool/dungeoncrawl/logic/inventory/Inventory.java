package com.codecool.dungeoncrawl.logic.inventory;


import java.util.ArrayList;
import java.util.List;

import com.codecool.dungeoncrawl.logic.gameobject.items.keys.Key;
import com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Gold;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import lombok.Getter;
import lombok.Setter;


public class Inventory {
    @Getter @Setter
    private List<Item> sack;
    private int inventorySize = 2;
    @Getter
    private List<Item> gold;

    @Getter @Setter
    private Item currentWepon;

    @Getter @Setter
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
    public int getGoldAmount() {
        return 50;
    }

    public boolean isInABag(String itemName ) {
        return sack.stream().anyMatch(i -> i.getClass().getSimpleName().equals(itemName));
    }

    private boolean isInventoryFull() {
        return sack.size()>=inventorySize;
    }

    private boolean isGold(Item item){
        return item instanceof Gold;
    }
}