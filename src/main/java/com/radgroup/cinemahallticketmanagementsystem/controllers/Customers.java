package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Customers extends CoreController implements Cont{
    @Override
    public void setView(Object data) {
        System.out.println("Customer view");
    }

    @FXML
    private void addNewCustomer(ActionEvent event) {
        showDialogBox("NewCustomer", "New Customer");
    }
}
