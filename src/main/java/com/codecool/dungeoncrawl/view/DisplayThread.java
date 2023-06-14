package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Game;
import com.codecool.dungeoncrawl.view.views.GameView;
import com.codecool.dungeoncrawl.view.views.ViewContainer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

public class DisplayThread extends Thread {

    private volatile boolean isPaused = false;

    @Getter @Setter
    private Pane container;
    @Getter @Setter
    private GameView gameView;


    public DisplayThread(Pane container, GameView gameView) {
        super();
        this.container = container;
        this.gameView = gameView;
    }


    public synchronized void pauseThread() {
        isPaused = true;
    }

    public synchronized void resumeThread() {
        isPaused = false;
        notify(); // Notify waiting threads to resume
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (isPaused) {
                    try {
                        wait(); // Wait until notified to resume
                    } catch (InterruptedException e) {
                        // Handle interrupted exception
                    }
                }
            }

            Platform.runLater(() -> {
                getGameView().refreshView();
                getContainer().getChildren().setAll(getGameView().getGamePane());
            });

            // Delay between turns (optional)
            try {
                Thread.sleep(100); // Adjust the delay as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
