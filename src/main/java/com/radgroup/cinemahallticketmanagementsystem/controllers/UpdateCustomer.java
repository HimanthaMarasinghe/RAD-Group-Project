package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import static com.radgroup.cinemahallticketmanagementsystem.controllers.Customers.testCusList;

public class UpdateCustomer extends dialogBox{

    @FXML
    private TextField updCusName;

    @FXML
    private TextField updCusPhone;

    @FXML
    private DatePicker updCusDoB;

    @FXML
    void updateCustomer(ActionEvent event) {
        String name = updCusName.getText();
        String phone = updCusPhone.getText();
        LocalDate dob = updCusDoB.getValue();

        //ToDo : Here it just mimic the update behaviour with a combination of delete and add methods. Should be properly implemented with the DAO file.
        testCusList.add(new Customer(name, phone, dob));

        dialog.setResult(ButtonType.OK);
        dialog.close();
    }

    @Override
    public void setDialogBox(Object dataObject){
        Customer curentCustomer = (Customer) dataObject;
        updCusName.setText(curentCustomer.getName());
        updCusPhone.setText(curentCustomer.getPhone());
        updCusDoB.setValue(curentCustomer.getDateOfBirth());


        //ToDo : Should be removed
        testCusList.remove(curentCustomer);
    }
}
