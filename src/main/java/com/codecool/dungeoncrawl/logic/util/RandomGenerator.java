package com.codecool.dungeoncrawl.logic.util;

import java.util.Random;


public class RandomGenerator {

    private static Random random = new Random();


    public static int getRandomInt(int bound){
        return random.nextInt(bound);
    }

    public static int getRandomIntBetween(int min, int max) {
        return random.nextInt(max-min) + min;
    }

    public static int throwADice() {
        return getRandomIntBetween(1, 6);
    }
}