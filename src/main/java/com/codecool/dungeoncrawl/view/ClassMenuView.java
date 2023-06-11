package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.App;
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

        // Create the images and buttons for hero classes
        Image warriorImage = new Image("classes/warrior.png");
        ImageView warriorImageView = createResizedImageView(warriorImage, 100, 100);
        Button warriorButton = createClassButton("Warrior");

        Image mageImage = new Image("classes/mage.png");
        ImageView mageImageView = createResizedImageView(mageImage, 100, 100);
        Button mageButton = createClassButton("Mage");

        Image rogueImage = new Image("classes/archer.png");
        ImageView rogueImageView = createResizedImageView(rogueImage, 100, 100);
        Button rogueButton = createClassButton("Rogue");

        VBox warriorVBox = new VBox();
        warriorVBox.getChildren().addAll(warriorImageView, warriorButton);
        warriorVBox.setAlignment(Pos.CENTER);

        VBox mageVBox = new VBox();
        mageVBox.getChildren().addAll(mageImageView, mageButton);
        mageVBox.setAlignment(Pos.CENTER);

        VBox rogueVBox = new VBox();
        rogueVBox.getChildren().addAll(rogueImageView, rogueButton);
        rogueVBox.setAlignment(Pos.CENTER);

        getChildren().addAll(
                warriorVBox,
                mageVBox,
                rogueVBox);
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
