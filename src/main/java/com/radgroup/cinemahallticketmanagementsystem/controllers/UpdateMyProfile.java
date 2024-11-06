package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.radgroup.cinemahallticketmanagementsystem.App.NameOfTheLogedUser;
import static com.radgroup.cinemahallticketmanagementsystem.App.userName;

public class UpdateMyProfile extends dialogBox{
    @FXML
    private TextField addressField;

    @FXML
    private TextField nameFeild;

    @FXML
    private TextField phoneFeild;

    @FXML
    private TextField usernameField;

    @FXML
    private void updateProfile(ActionEvent event) {
        UserDAO UDAO = new UserDAOImpl();
        String username = usernameField.getText();
        String name = nameFeild.getText();
        String address = addressField.getText();
        String phone = phoneFeild.getText();
        User user = new User(username, name, address, phone);
        UDAO.updateUserDetails(userName, user);
        userName = user.getUsername();
        NameOfTheLogedUser.set(user.getName());
        dialog.setResult(ButtonType.OK);
        dialog.close();
    }

    @Override
    public void setDialogBox(Object dataObject) {
        User myProfile = (User) dataObject;
        usernameField.setText(userName);
        nameFeild.setText(myProfile.getName());
        addressField.setText(myProfile.getAddress());
        phoneFeild.setText(myProfile.getPhone());
    }
}
