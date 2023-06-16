package com.codecool.dungeoncrawl.logic.gameobject.actors.player;

import java.util.ArrayList;
import java.util.List;

import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.items.treasures.Treasures;
import com.codecool.dungeoncrawl.logic.messages.Message;
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


    public Inventory() {
        this.sack = new ArrayList<>();
        this.gold = new ArrayList<>();
    }

    public void putItemToInventory(Item item){
        if (item instanceof Treasures){
            gold.add((Treasures) item);
        } else if (!isInventoryFull()){
            sack.add(item);
            inventorySize--;
        } else {
            Message.getInstance().setActualMessage("Inventory is full!");
            System.out.println("Inventory is full!");
        }
    }

    public int getGoldAmount() {
        return gold.stream()
                .mapToInt(Treasures::getValue)
                .sum();
    }

    public boolean isInABag(Item item) {
        return sack.contains(item);
//        return sack.stream().anyMatch(i -> i.getClass().getSimpleName().equals(item));
    }


    private boolean isInventoryFull() {
        return sack.size()>=inventorySize;
    }

    public void useItem(Item item) {
        //item.useIt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
//        sb.append("Sack: ").append("\n");
        for (Item item : sack) {

            sb.append(sack.indexOf(item) + " : " + item.toString()).append("\n");
        }
        sb.append("Inventory Size: ").append(inventorySize).append("\n");
        sb.append("To open inventory press 'i'").append("\n");
        sb.append("Gold: ").append(getGoldAmount()).append("\n");
        sb.append("Current Weapon: ").append(currentWeapon).append("\n");
        sb.append("Current Armor: ").append(currentArmor).append("\n");
        return sb.toString();
    }

    public void removeFromInventory(Item item) {
        sack.remove(item);
        inventorySize++;
    }
}