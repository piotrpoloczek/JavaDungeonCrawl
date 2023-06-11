package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.App;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

        Button playButton = new javafx.scene.control.Button("Play");
        playButton.setOnAction(event -> appView.showGameView());

        getChildren().addAll(playButton);
    }

}
