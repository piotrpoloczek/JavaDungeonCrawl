package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.view.views.GameView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

public class DisplayTask extends Task<Void> {

    private volatile boolean isPaused = false;

    @Getter
    @Setter
    private Pane container;
    @Getter @Setter
    private GameView gameView;


    public DisplayTask(Pane container, GameView gameView) {
        super();
        this.container = container;
        this.gameView = gameView;
    }

    public void pauseTask() {
        isPaused = true;
    }

    public void resumeTask() {
        isPaused = false;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            if (isPaused) {
                updateMessage("Paused");
                while (isPaused) {
                    Thread.sleep(100); // Sleep for a short time while paused
                }
                updateMessage("Resumed");
            }

            Platform.runLater(() -> {
                getGameView().refreshView();
                getContainer().getChildren().setAll(getGameView().getGamePane());
            });

            try {
                Thread.sleep(100); // Adjust the delay as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check if the task is cancelled
            if (isCancelled()) {
                break;
            }
        }

        return null;
    }
}
