package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MyProfile extends CoreController implements Cont{

    @Override
    public void setView(Object data) {
        System.out.println("MyProfile setView");
    }

    @FXML
    private void updatePassword(ActionEvent event) {
        System.out.println("MyProfile updatePassword");
        showDialogBox("UpdPwd","Update Password");
    }
}
