package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

import static com.radgroup.cinemahallticketmanagementsystem.App.userName;

public class ChangePassword extends dialogBox {
    @FXML
    private PasswordField currentPwdField;

    @FXML
    private PasswordField newPwdField;

    @FXML
    private PasswordField reEnterNewPwdField;

    @FXML
    private void updPwd(ActionEvent event) {
        String currentPwd = currentPwdField.getText();
        String newPwd = newPwdField.getText();
        String reEnterNewPwd = reEnterNewPwdField.getText();
        UserDAO UDAO = new UserDAOImpl();
        User user = UDAO.getUser(userName);
        if(currentPwd.isEmpty() || newPwd.isEmpty() || reEnterNewPwd.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Password cannot be empty");
            alert.showAndWait();
        }
        else if(currentPwd.equals(user.getPassword())) {
            if(currentPwd.equals(newPwd)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("You can not change the password into current password");
                alert.showAndWait();
            }
            else if(newPwd.equals(reEnterNewPwd)) {
                user.setPassword(newPwd);
                UDAO.updateUserPassword(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Password changed successfully");
                alert.showAndWait();
                dialog.setResult(ButtonType.OK);
                dialog.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("New password and re-entered new password doesn't match");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Password");
            alert.setContentText("Provided current password is incorrect");
            alert.showAndWait();
        }
    }
}
