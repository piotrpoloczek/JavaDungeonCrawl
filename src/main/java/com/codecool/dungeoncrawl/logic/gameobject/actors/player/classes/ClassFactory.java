package com.codecool.dungeoncrawl.logic.gameobject.actors.player.classes;

public class ClassFactory {


    public static Class getClass(String name) {
        switch (name) {
            case "Mage":
                return new MageClass();
            default:
                return new WarriorClass();
        }
    }
}
