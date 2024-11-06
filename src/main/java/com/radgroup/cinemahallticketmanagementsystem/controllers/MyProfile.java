package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.radgroup.cinemahallticketmanagementsystem.App.userName;

public class MyProfile extends CoreController implements Cont{

    @FXML
    private TextField addressField;

    @FXML
    private TextField nameFeild;

    @FXML
    private TextField phoneFeild;

    @FXML
    private TextField usernameField;

    @FXML
    void handleUpdateProfileButton(ActionEvent event) {
        System.out.println("Update profile button clicked");
        UserDAO myUserDao = new UserDAOImpl();
        showDialogBox("UpdateMyProfile", "Update My Profile", myUserDao.getUser(userName));
        setView(null);
    }

    @Override
    public void setView(Object data) {
        System.out.println("MyProfile setView");
        UserDAO myUserDao = new UserDAOImpl();
        User myDetail = myUserDao.getUser(userName);
        usernameField.setText(myDetail.getUsername());
        nameFeild.setText(myDetail.getName());
        phoneFeild.setText(myDetail.getPhone());
        addressField.setText(myDetail.getAddress());
    }

    @FXML
    private void updatePassword(ActionEvent event) {
        System.out.println("MyProfile updatePassword");
        showDialogBox("ChangePassword","Update Password");
    }
}
