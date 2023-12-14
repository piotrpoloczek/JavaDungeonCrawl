package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.classes.ClassFactory;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

public class ClassView {

    // containers
    @Getter
    @Setter
    private BorderPane mainPane;
    @Getter
    @Setter
    private Canvas canvas;
    @Getter
    @Setter
    private HBox mainBox;
    private VBox centerPane;
    @Getter @Setter
    private Game game;


    public ClassView(Game game) {
        this.game = game;
        this.mainPane = new BorderPane();
        mainPane.setPrefSize(600, 600);
        this.mainBox = new HBox();
        this.centerPane = new VBox();
        initializeUI();
    }

    private void initializeUI() {
        mainBox.setAlignment(Pos.BASELINE_CENTER);
        mainBox.setSpacing(10);

        VBox warriorVBox = prepareVBox("classes/warrior.png", "Warrior");
        VBox mageVBox = prepareVBox("classes/mage.png", "Mage");
        VBox archerVBox = prepareVBox("classes/archer.png", "Archer");

        mainBox.getChildren().addAll(
                warriorVBox,
                mageVBox,
                archerVBox
        );
        centerPane.setAlignment(Pos.CENTER);
        centerPane.getChildren().add(mainBox);
        mainPane.setCenter(centerPane);
    }

    private VBox prepareVBox (String imagePath, String className){
        VBox warriorVBox = new VBox();

        // Create the images and buttons for hero classes
        Image warriorImage = new Image(imagePath);
        ImageView warriorImageView = createResizedImageView(warriorImage, 100, 100);
        Button warriorButton = createClassButton(className);

        warriorVBox.getChildren().addAll(warriorImageView, warriorButton);
        return warriorVBox;
    }

    private Button createClassButton (String className){
        Button button = new Button(className);
        button.setOnAction(event -> {
            System.out.println("Selected Class: " + className);

            // choose class based on user choice
            getGame().getCurrentMap().getPlayer().setPlayerHeroClass(
                    ClassFactory.getClass(className)
            );

            ViewContainer.getInstance().showGameView();
        });
        return button;
    }

    private ImageView createResizedImageView (Image image,double width, double height){
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}
