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

public class UpdateCustomer extends dialogBox{

    @FXML
    private TextField updCusName;

    @FXML
    private TextField updCusPhone;

    @FXML
    private DatePicker updCusDoB;


    private String previousPhoneInput;
    private Customer curentCustomer;


    @FXML
    void updateCustomer(ActionEvent event) {
        String name = updCusName.getText();
        String phone = updCusPhone.getText();
        LocalDate dob = updCusDoB.getValue();

        if(name.trim().isEmpty() || phone.trim().isEmpty() || dob == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Text fields cannot be empty");
            alert.showAndWait();
            return;
        }

        CustomerDAO CDAO = new CustomerDAOImpl();
        Customer newC = new Customer(name, phone, dob);



        if(updCusPhone.getText().length() != 10){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Phone number must be 10 digits");
            alert.showAndWait();
            return;
        }else if(curentCustomer.areAttributesEqual(newC)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No new data provided");
            alert.showAndWait();
            return;
        }else{
            Customer customer = CDAO.getCustomer(phone);
            if(customer != null && !customer.getPhone().matches(curentCustomer.getPhone())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Phone already exists in the Database. Use deferent phone number");
                alert.showAndWait();
                return;
            }
        }

        CDAO.updateCustomer(curentCustomer.getPhone(), newC);

        dialog.setResult(ButtonType.OK);
        dialog.close();

    }

    @Override
    public void setDialogBox(Object dataObject){
        curentCustomer = (Customer) dataObject;
        updCusName.setText(curentCustomer.getName());
        updCusPhone.setText(curentCustomer.getPhone());
        updCusDoB.setValue(curentCustomer.getDateOfBirth());
    }

    @FXML
    void handleCustomerPhoneInsert(KeyEvent event) {
        String phone = updCusPhone.getText();
        if (phone.length() > 10 || !phone.matches("\\d*"))
            updCusPhone.setText(previousPhoneInput);
        else
            previousPhoneInput = phone;
    }
}
