package com.codecool.dungeoncrawl.logic.messages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Message {

    private static Message instance;

    // TODO: observer design pattern

    private List<String> list;
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
