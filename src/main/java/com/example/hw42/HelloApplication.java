/**
 * this main method that will launch the GUI
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    /**
     * This method sets the scene title and the size of the window. It also loads the first fxml page.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("RU Cafe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}