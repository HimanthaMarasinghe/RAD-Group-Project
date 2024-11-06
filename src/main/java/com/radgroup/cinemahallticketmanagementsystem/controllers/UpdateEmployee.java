package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.radgroup.cinemahallticketmanagementsystem.controllers.Customers.testCusList;
import static com.radgroup.cinemahallticketmanagementsystem.controllers.Employees.testUsersList;

public class UpdateEmployee extends dialogBox {

    @FXML
    private TextField updEmpAdd;

    @FXML
    private TextField updEmpName;

    @FXML
    private TextField updEmpPhone;

    @FXML
    private TextField updEmpUsername;

    @FXML
    void updEmployees(ActionEvent event) {
        String username = updEmpUsername.getText();
        String name = updEmpName.getText();
        String phone = updEmpPhone.getText();
        String address = updEmpAdd.getText();

        //ToDo : Here it just mimic the update behaviour with a combination of delete and add methods. Should be properly implemented with the DAO file.
        testUsersList.add(new User(username, name, address, phone));

        dialog.setResult(ButtonType.OK);
        dialog.close();

    }

    public void setDialogBox(Object dataObject){
        User currentUser = (User) dataObject;
        updEmpUsername.setText(currentUser.getUsername());
        updEmpName.setText(currentUser.getName());
        updEmpPhone.setText(currentUser.getPhone());
        updEmpAdd.setText(currentUser.getAddress());

        //ToDo : Should be removed. This method is here because Arraylists does not support updates
        testUsersList.remove(currentUser);
    }
}
