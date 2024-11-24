package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

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

    private User currentUser;

    @FXML
    void updEmployees(ActionEvent event) {
        String username = updEmpUsername.getText();
        String name = updEmpName.getText();
        String phone = updEmpPhone.getText();
        String address = updEmpAdd.getText();

        if(username != null && name != null && address != null && phone != null) {
            UserDAO UDAO = new UserDAOImpl();
            User userWithSameId = UDAO.getUser(username);
            User newUser = new User(username, name, address, phone);
//
//            System.out.println(newUser.getUsername());
//            System.out.println(newUser.getName());
//            System.out.println(newUser.getPhone());
//            System.out.println(newUser.getAddress());
//
//            System.out.println(currentUser.getUsername());
//            System.out.println(currentUser.getName());
//            System.out.println(currentUser.getPhone());
//            System.out.println(currentUser.getAddress());
//
//            System.out.println(newUser.areAttributesEqual(currentUser));

            if(!newUser.areAttributesEqual(currentUser)) {
                if (userWithSameId == null || userWithSameId.areAttributesEqual(currentUser)) {
                    UDAO.updateUserDetails(currentUser.getUsername(), newUser);
                    dialog.setResult(ButtonType.OK);
                    dialog.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Username already exists");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No new Data provided");
                alert.showAndWait();
            }
        }
    }

    public void setDialogBox(Object dataObject){
        currentUser = (User) dataObject;
        updEmpUsername.setText(currentUser.getUsername());
        updEmpName.setText(currentUser.getName());
        updEmpPhone.setText(currentUser.getPhone());
        updEmpAdd.setText(currentUser.getAddress());

        //ToDo : Should be removed. This method is here because Arraylists does not support updates
        testUsersList.remove(currentUser);
    }
}
