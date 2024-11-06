package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.radgroup.cinemahallticketmanagementsystem.controllers.Employees.testUsersList;

public class NewEmployee extends dialogBox {

    @FXML
    private TextField newEmpUsername;

    @FXML
    private void addEmployees(ActionEvent event) {

        //Add user in to the db
        testUsersList.add(new User(newEmpUsername.getText()));

        dialog.setResult(ButtonType.OK);
        dialog.close();
    }
}
