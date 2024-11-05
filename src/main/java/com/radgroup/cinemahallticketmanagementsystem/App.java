package com.radgroup.cinemahallticketmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    /**
     * This variable is set when the user log in. Can be used anywhere in the code.
     */
    public static String userName;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sign In!");
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.setX(50);
        stage.setY(50);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}