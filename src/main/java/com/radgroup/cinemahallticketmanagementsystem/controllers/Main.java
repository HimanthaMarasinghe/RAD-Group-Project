package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Main implements Cont{
    @FXML
    private Label welcome;

    @Override
    public void setView(Object data) {
        if (data instanceof String) {
            welcome.setText("Welcome "+(String) data); // Set the username on the label
        }
    }
}
