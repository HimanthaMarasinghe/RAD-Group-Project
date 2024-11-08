package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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

        if(username != null && name != null && add != null && phone != null) {
            UserDAO UDAO = new UserDAOImpl();
            User userWithSameId = UDAO.getUser(username);
            if(userWithSameId == null) {
                UDAO.addUser(new User(username, name, add, phone));
                dialog.setResult(ButtonType.OK);
                dialog.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }

    }
}
