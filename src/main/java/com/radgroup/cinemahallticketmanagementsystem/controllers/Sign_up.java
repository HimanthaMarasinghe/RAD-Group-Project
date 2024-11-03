package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Sign_up {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}