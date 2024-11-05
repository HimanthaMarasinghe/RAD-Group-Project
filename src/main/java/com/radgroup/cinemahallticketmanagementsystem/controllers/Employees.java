package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Employees extends CoreController implements Cont{

    @FXML
    private Label test;
    
    public void setView(Object data) {
        System.out.println("Employees controller works");
//        test.setText("Emp Works");
    }

    @FXML
    void addNewEmployee(ActionEvent event) {
        showDialogBox("NewEmployee","Add New Employee");
    }

}
