package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

public class UpdatePassword extends dialogBox {
    @FXML
    void updPwd(ActionEvent event) {
        System.out.println("Add button clicked");
        dialog.setResult(ButtonType.OK); // Set result to OK
        dialog.close();
    }

    @FXML
    void cancel(ActionEvent actionEvent) {
        System.out.println("Cancel button clicked");
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }
}
