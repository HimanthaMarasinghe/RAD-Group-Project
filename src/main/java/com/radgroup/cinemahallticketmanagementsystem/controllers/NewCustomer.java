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
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;

public class NewCustomer extends dialogBox{

    @FXML
    private TextField NewCusName;

    @FXML
    private TextField NewCusPhone;

    @FXML
    private DatePicker newCusDOB;

    private String previousPhoneInput;
    private boolean phoneAlreadyPresent = false;

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
            return;
        }



        if(NewCusPhone.getText().length() != 10){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Phone number must be 10 digits");
            alert.showAndWait();
            return;
        }else {
            CustomerDAO CDAO = new CustomerDAOImpl();
            Customer customer = CDAO.getCustomer(phone);
            if((customer != null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Phone already exists in the Database. Use deferent phone number");
                alert.showAndWait();
                return;
            }
        }

        CustomerDAO CDAO = new CustomerDAOImpl();
        CDAO.addCustomer(new Customer(name, phone, dob));

        dialog.setResult(ButtonType.OK);
        dialog.close();

    }

    @FXML
    void handleCustomerPhoneInsert(KeyEvent event) {
        String phone = NewCusPhone.getText();
        if (phone.length() > 10 || !phone.matches("\\d*"))
            NewCusPhone.setText(previousPhoneInput);
        else
            previousPhoneInput = phone;
    }
}
