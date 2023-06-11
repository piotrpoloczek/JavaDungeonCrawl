package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.App;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceBox;


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
