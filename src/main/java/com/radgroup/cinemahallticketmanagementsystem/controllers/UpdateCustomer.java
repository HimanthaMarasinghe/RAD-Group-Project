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

public class UpdateCustomer extends dialogBox{

    @FXML
    private TextField updCusName;

    @FXML
    private TextField updCusPhone;

    @FXML
    private DatePicker updCusDoB;

    private String currentCustomerPhone;

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
        }else{
            CustomerDAO CDAO = new CustomerDAOImpl();
            CDAO.updateCustomer(currentCustomerPhone ,new Customer( name, phone, dob));

            dialog.setResult(ButtonType.OK);
            dialog.close();
        }

    }

    @Override
    public void setDialogBox(Object dataObject){
        Customer curentCustomer = (Customer) dataObject;
        currentCustomerPhone = curentCustomer.getPhone();
        updCusName.setText(curentCustomer.getName());
        updCusPhone.setText(curentCustomer.getPhone());
        updCusDoB.setValue(curentCustomer.getDateOfBirth());


        //ToDo : Should be removed. This method is here because Arraylists does not support updates
        testCusList.remove(curentCustomer);
    }
}
