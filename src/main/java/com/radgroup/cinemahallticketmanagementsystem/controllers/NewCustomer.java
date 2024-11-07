package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import static com.radgroup.cinemahallticketmanagementsystem.controllers.Customers.testCusList;

public class NewCustomer extends dialogBox{

    @FXML
    private TextField NewCusName;

    @FXML
    private TextField NewCusPhone;

    @FXML
    private DatePicker newCusDOB;

    @FXML
    void addCustomer(ActionEvent event) {
        String name = NewCusName.getText();
        String phone = NewCusPhone.getText();
        LocalDate dob = newCusDOB.getValue();

        //Add customer in to the DB
        testCusList.add(new Customer(name, phone, dob));

        dialog.setResult(ButtonType.OK);
        dialog.close();
    }
}
