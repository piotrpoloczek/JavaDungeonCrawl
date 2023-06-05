package com.codecool.dungeoncrawl.logic.actors;

import java.util.Scanner;

public class PlayerFactoryConsoleImpl implements PlayerFactory{

    @Override
    public String getPlayerNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide name for your hero");
        return scanner.nextLine();
    }
}
