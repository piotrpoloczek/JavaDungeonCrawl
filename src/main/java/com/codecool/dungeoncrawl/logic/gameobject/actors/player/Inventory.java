package com.codecool.dungeoncrawl.logic.gameobject.actors.player;


import java.util.ArrayList;
import java.util.List;

import com.codecool.dungeoncrawl.logic.gameobject.items.keys.Key;
import com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Gold;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Treasures;
import lombok.Getter;
import lombok.Setter;


public class Inventory {
    @Getter @Setter
    private List<Item> sack;
    private int inventorySize = 10;
    @Getter
    private List<Treasures> gold;

    @Getter @Setter
    private Item currentWeapon;

    @Getter @Setter
    private Item currentArmor;

    //TODO ? limit space in sack

    public Inventory() {
        this.sack = new ArrayList<>();
        this.gold = new ArrayList<>();
    }

    public void putItemToInventory(Item item){
        if (item instanceof Treasures){
            gold.add((Treasures) item);
        } else if (!isInventoryFull()){
            sack.add(item);
        } else {
            System.out.println("Inventory is full!");
        }
    }

    //TODO geter do zawracania ilości złota (wielkości listy)
    public int getGoldAmount() {
        return gold.stream()
                .mapToInt(Treasures::getValue)
                .sum();
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sack: ").append("\n");
        for (Item item : sack) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Inventory Size: ").append(inventorySize).append("\n");
        sb.append("Gold: ").append(getGoldAmount()).append("\n");
        sb.append("Current Weapon: ").append(currentWeapon).append("\n");
        sb.append("Current Armor: ").append(currentArmor).append("\n");
        return sb.toString();
    }
}