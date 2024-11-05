package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Employees implements Cont{

    @FXML
    private Label test;
    
    public void setView(Object data) {
        System.out.println("Employees controller works");
//        test.setText("Emp Works");
    }

}
