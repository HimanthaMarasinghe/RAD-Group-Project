package com.radgroup.cinemahallticketmanagementsystem;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    /**
     * This variable is set when the user log in. Can be used anywhere in the code.
     */
    public static String userName;

    /**
     * This string property is assigned to the welcome text. When user log in this variable is set to his name.
     * In user's profile update method, this variable is again set to user's new name.
     * Since this variable is a StringProperty, any change made to this variable will automatically update the welcome text in the ui.
     */
    public static StringProperty NameOfTheLogedUser = new SimpleStringProperty();

    /**
     * There are 2 roles in the application. Manager and Employee.
     * This variable is set when the user log in. Only if the logged-in user's role is a Manager, this variable is set to True.
     * There are options that only the Manager can perform in this application, and those options are only available if this variable is set to true.
     */
    public static boolean isManager = false;

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