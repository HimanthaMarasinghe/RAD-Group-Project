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
    private TextField newEmpName;

    @FXML
    private TextField newEmpAdd;

    @FXML
    private TextField newEmpPhone;


    @FXML
    private void addEmployees(ActionEvent event) {

        //Add user in to the db
        String username = newEmpUsername.getText();
        String name = newEmpName.getText();
        String add = newEmpAdd.getText();
        String phone = newEmpPhone.getText();
        testUsersList.add(new User(username, name, add, phone));
        dialog.setResult(ButtonType.OK);
        dialog.close();
    }
}
