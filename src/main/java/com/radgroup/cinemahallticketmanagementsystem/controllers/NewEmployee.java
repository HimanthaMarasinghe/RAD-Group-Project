package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXML;

public class NewEmployee extends dialogBox {

    @FXML
    void addEmployees(ActionEvent event) {
        dialog.setResult(ButtonType.OK);
        dialog.close();
    }
}
