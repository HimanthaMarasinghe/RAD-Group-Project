package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Home implements DataReceiver{
    @FXML
    private Text welcome;

    @Override
    public void receiveData(Object data) {
        if(data instanceof String){
            welcome.setText("Welcome "+(String) data); // Set the username on the label
        }
    }
}
