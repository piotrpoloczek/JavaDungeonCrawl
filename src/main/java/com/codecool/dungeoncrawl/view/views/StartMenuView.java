package com.codecool.dungeoncrawl.view.views;

import com.codecool.dungeoncrawl.App;
import com.codecool.dungeoncrawl.logic.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMenuView {

        private Stage primaryStage;
        private Game game;

        public void StartMenu(Stage primaryStage) {
            this.primaryStage = primaryStage;
            this.game = new Game();
            initializeUI();
        }

        private void initializeUI() {
            BorderPane mainPane = new BorderPane();
            mainPane.setPrefSize(600, 600);

            HBox mainBox = new HBox();
            mainBox.setAlignment(Pos.BASELINE_CENTER);
            mainBox.setSpacing(10);

            VBox centerPane = new VBox();
            centerPane.setAlignment(Pos.CENTER);

            VBox newGameVBox = prepareVBox("new_game.png", "New Game");
            VBox quitVBox = prepareVBox("quit.png", "Quit");
            VBox wallOfFameVBox = prepareVBox("wall_of_fame.png", "Wall of Fame");

            mainBox.getChildren().addAll(newGameVBox, quitVBox, wallOfFameVBox);
            centerPane.getChildren().add(mainBox);

            mainPane.setCenter(centerPane);

            Scene scene = new Scene(mainPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Start Menu");
            primaryStage.show();
        }

        private VBox prepareVBox(String imagePath, String buttonLabel) {
            VBox vBox = new VBox();

            // Create the image and button
            Button button = new Button(buttonLabel);
            button.setOnAction(event -> handleButtonAction(buttonLabel));

            // Load the image
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            vBox.getChildren().addAll(imageView, button);
            return vBox;
        }

        private void handleButtonAction(String buttonLabel) {
            if (buttonLabel.equals("New Game")) {
                ClassView classView = new ClassView(game);

            } else if (buttonLabel.equals("Quit")) {
                primaryStage.close();

            } else if (buttonLabel.equals("Wall of Fame")) {

            }
        }


//            // Create the primary stage
//            Stage primaryStage = new Stage();
//
//            // Create the start menu
//            StartMenu startMenu = new StartMenu(primaryStage, game);
        }
