package com.codecool.dungeoncrawl.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClassMenuView extends HBox {

    private AppView appView;

    public ClassMenuView(AppView appView) {
        this.appView = appView;
        initializeUI();
    }

    private void initializeUI() {
        setSpacing(10);
        setAlignment(Pos.CENTER);

        VBox warriorVBox = prepareVBox("classes/warrior.png", "Warrior");
        VBox mageVBox = prepareVBox("classes/mage.png", "Mage");
        VBox archerVBox = prepareVBox("classes/archer.png", "Archer");

        getChildren().addAll(
                warriorVBox,
                mageVBox,
                archerVBox
        );
    }

    private VBox prepareVBox(String imagePath, String className) {
        VBox warriorVBox = new VBox();

        // Create the images and buttons for hero classes
        Image warriorImage = new Image(imagePath);
        ImageView warriorImageView = createResizedImageView(warriorImage, 100, 100);
        Button warriorButton = createClassButton(className);

        warriorVBox.getChildren().addAll(warriorImageView, warriorButton);
        warriorVBox.setAlignment(Pos.CENTER);
        return warriorVBox;
    }

    private Button createClassButton(String className) {
        Button button = new Button(className);
        button.setOnAction(event -> {
            System.out.println("Selected Class: " + className);
        });
        return button;
    }

    private ImageView createResizedImageView(Image image, double width, double height) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}
