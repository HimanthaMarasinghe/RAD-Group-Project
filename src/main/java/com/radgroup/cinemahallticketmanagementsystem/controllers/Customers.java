package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.CustomerDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.CustomerDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Customers extends CoreController implements Cont{

    @FXML
    private TableColumn<Customer, String> cusNameCol;

    @FXML
    private TableColumn<Customer, String> cusPhoneCol;

    @FXML
    private TableColumn<Customer, String> cusDobCol;

    @FXML
    private TableView<Customer> cusTable;

    private ObservableList<Customer> CusList;

    public void initialize() {
        System.out.println("Customers Initializes");

        cusNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        cusPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        cusDobCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("dateOfBirth"));

        CusList = FXCollections.observableArrayList();
        cusTable.setItems(CusList);
    }

    @Override
    public void setView(Object data) {
        System.out.println("Customer view");
        refresh();
    }

    @FXML
    private void addNewCustomer(ActionEvent event) {
        showDialogBox("NewCustomer", "New Customer");
        refresh();
        System.out.println("Customer List Refreshed");
    }

    @FXML
    private void refresh(ActionEvent event) {
        refresh();
    }

    // The above method is for the Refresh Button Click event and requires an ActionEvent as an argument.
    // The method below is a general Refresh method with no arguments that can be called inside a Button Click event as well as inside the initialize method.

    private void refresh() {
        CusList.clear();

        CustomerDAO CDAO = new CustomerDAOImpl();
        CusList.addAll(CDAO.getAllCustomers());
    }

    @FXML
    void deleteCus(ActionEvent event) {
        Customer cusSelected = (Customer) cusTable.getSelectionModel().getSelectedItem();
        if (cusSelected == null) {
            System.out.println("No customer selected");
        }
        else{
            System.out.println(cusSelected.getName());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setContentText("Are you sure you want to delete "+cusSelected.getName()+" From The Database?");
            Optional<ButtonType> answer = alert.showAndWait();
            if(answer.get() == ButtonType.OK){


                CustomerDAO CDAO = new CustomerDAOImpl();
                CDAO.deleteCustomer(cusSelected.getPhone());

                Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                deleteAlert.setTitle("Delete");
                deleteAlert.setContentText("Customer "+cusSelected.getName()+" has been deleted Successfully");
                deleteAlert.show();
            }
        }
        refresh();
    }

    @FXML
    void updateCus(ActionEvent event) {
        Customer cusSelected = (Customer) cusTable.getSelectionModel().getSelectedItem();
        if (cusSelected == null) {
            System.out.println("No customer selected");
        }
        else{
            showDialogBox("UpdateCustomer", "Update Customer", cusSelected);
            refresh();
            System.out.println(cusSelected.getName());
        }

    }
}
