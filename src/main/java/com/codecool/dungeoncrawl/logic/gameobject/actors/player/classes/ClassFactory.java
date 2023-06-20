package com.codecool.dungeoncrawl.logic.gameobject.actors.player.classes;

public class ClassFactory {


    public static HeroClass getClass(String name) {
        switch (name) {
            case "Mage":
                return new MageHeroClass();
            default:
                return new WarriorHeroClass();
        }
    }
}
