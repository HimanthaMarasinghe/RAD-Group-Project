package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

public class NewTicket extends dialogBox{

    @FXML
    public void addNewTicket(ActionEvent actionEvent) {
        System.out.println("Add button clicked");
        dialog.setResult(ButtonType.OK); // Set result to OK
        dialog.close();
    }
}
