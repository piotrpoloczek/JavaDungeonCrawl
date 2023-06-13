//package com.codecool.dungeoncrawl.logic.gameobject.actors;
//
//import com.codecool.dungeoncrawl.logic.util.RandomGenerator;
//
//public abstract class Fight {
//
//    private Actor thisActor;
//
//    public Fight(Actor actor) {
//        this.thisActor = actor;
//    }
//
//    public void attack(Actor actor){
//
//        System.out.println( this.thisActor.getName() + " is attacking " + actor.getName());
//
//        if(isDefenceSuccessful(actor)) {
//            System.out.println(actor.getName() + " pushes back the attack!");
//            System.out.println();
//        }
//        else {
//            int damage = setHarm(actor, thisActor.getAttack());
//            System.out.println("Damage: " + damage + ", " + actor.getName() + " health: " + actor.getHealth());
//            System.out.println();
//        }
//
//        if (actor.isAlive()) {
//            actor.counterAttack(this.thisActor);
//        }
//        else {
//            System.out.println(actor.getName() + " died!");
//            actor.cell.setGameObject(null);
//        }
//    }
//
//    private void counterAttack(Actor actor) {
//        actor.defense(this.attack);
//    }
//
//    public void defense(int attack) {
//        health = this.health - attack;
//    }
//
//    private int setHarm(Actor actor, int damage) {
//        if(isHitCritical()) {
//            System.out.println("Critical hit!");
//            actor.setHealth((actor.getHealth() - damage * 2));
//            damage *= 2;
//        }
//        else {
//            actor.setHealth(actor.getHealth() - damage);
//        }
//        return damage;
//    }
//
//    private boolean isHitCritical() {
//        int diceThrow = RandomGenerator.throwADice() + RandomGenerator.throwADice();
//        return diceThrow * 10 >= 110 - dexterity * 4;
//    }
//
//    private boolean isDefenceSuccessful(Actor actor) {
//        int diceThrow = RandomGenerator.throwADice() + RandomGenerator.throwADice();
//        return diceThrow * 10 >= 110 - actor.getDefense() * 4;
//    }
//}
