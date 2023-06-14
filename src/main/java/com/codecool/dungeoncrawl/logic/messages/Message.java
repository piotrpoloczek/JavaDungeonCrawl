package com.codecool.dungeoncrawl.logic.messages;

import lombok.Getter;
import lombok.Setter;

public class Message {

    private static Message instance;
    @Getter @Setter
    private String actualMessage;

    private Message() {}

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
        }
        return instance;
    }



}
