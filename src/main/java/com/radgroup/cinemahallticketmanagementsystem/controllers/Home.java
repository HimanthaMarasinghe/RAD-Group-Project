package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Home implements DataReceiver{
    @FXML
    private Label welcome;

    @Override
    public void receiveData(Object data) {
        if (data instanceof String) {
            welcome.setText((String) data); // Set the username on the label
        }
    }
}
