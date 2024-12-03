package org.game;

import javafx.application.Application;
import javafx.stage.Stage;
import org.game.controller.Controller;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Controller(stage);
    }

}
