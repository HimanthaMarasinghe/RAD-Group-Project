package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.CustomerDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.CustomerDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        if(name.trim().isEmpty() || phone.trim().isEmpty() || dob == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Input fields cannot be empty");
            alert.showAndWait();
        }else {
            CustomerDAO CDAO = new CustomerDAOImpl();
            CDAO.addCustomer(new Customer(0, name, phone, dob));

            dialog.setResult(ButtonType.OK);
            dialog.close();
        }
    }
}
