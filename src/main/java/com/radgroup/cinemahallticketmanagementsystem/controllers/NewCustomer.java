package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

public class NewCustomer extends dialogBox{

    @FXML
    void addCustomer(ActionEvent event) {
        dialog.setResult(ButtonType.OK);
        dialog.close();
    }

    @FXML
    void cancel(ActionEvent event) {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }
}
