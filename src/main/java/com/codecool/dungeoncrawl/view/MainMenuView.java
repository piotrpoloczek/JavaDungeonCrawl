package com.codecool.dungeoncrawl.view;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class MainMenuView extends VBox {

    private AppView appView;

    public MainMenuView(AppView appView) {
        this.appView = appView;
        initializeUI();
    }

    private void initializeUI() {
        setSpacing(10);
        setPadding(new Insets(10));

        Button playButton = new Button("Play");
        playButton.setOnAction(event -> appView.showGameView());

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();

        // Start button
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            String playerName = nameTextField.getText();
        });

        getChildren().addAll(playButton, nameLabel, startButton);
    }
}
