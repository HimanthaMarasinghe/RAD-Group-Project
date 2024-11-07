package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.App;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import com.radgroup.cinemahallticketmanagementsystem.util.SceneSwitcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Sign_in {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void viewHome(ActionEvent event) {
        String usernameInput = username.getText();
        String passwordInput = password.getText();

        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a valid username and password");
            alert.showAndWait();
        }
        else{
            try {
                UserDAO U = new UserDAOImpl();
                User user = U.getUser(usernameInput);
                if (user != null) {
                    if(user.getPassword().equals(passwordInput)) {

                        //Setting global variables
                        App.userName = usernameInput;
                        App.NameOfTheLogedUser = new SimpleStringProperty(user.getName());
                        App.isManager = user.getRole().equals("Manager");

                        SceneSwitcher.switchScene(event, "Main", null);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Wrong Password");
                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Username is wrong");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle error (e.g., show an error message to the user)
            }
        }
    }
}