package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Optional;

public class Employees extends CoreController implements Cont{

    @FXML
    private TableColumn<User, String> empCol;

    @FXML
    private TableView<User> empTable;

    private ObservableList<User> empList;

    //ToDo : This arrayList is used as the DB. Should be removed after UserDOBImpl class is created
    public static ArrayList<User> testUsersList = new ArrayList<>();

    public void initialize() {
        System.out.println("Customers Initializes");

        //ToDo : This code block is hard coded to create initial data. Should be removed after CustomerDOBImpl class is created
        testUsersList.clear();
        testUsersList.add(new User("TestUser", "PPP", "Emp"));
        testUsersList.add(new User("TestUser", "PPP", "Emp"));
        testUsersList.add(new User("TestUser", "PPP", "Emp"));
        testUsersList.add(new User("TestUser", "PPP", "Emp"));



        empCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        empList = FXCollections.observableArrayList();
//        empList.addAll();

        empTable.setItems(empList);
    }

    @FXML
    private void refreshEmpList(ActionEvent event) {
        refresh();
    }

    private void refresh(){
        empList.clear();
        empList.addAll(getAllUsers());
    }
    
    public void setView(Object data) {
        System.out.println("Employees controller works");
        refresh();
    }

    @FXML
    void addNewEmployee(ActionEvent event) {
        showDialogBox("NewEmployee","Add New Employee");
        refresh();
    }

    //ToDo : This method should be deleted and similar method should be implemented in a DAO class.
    private ArrayList<User> getAllUsers() {
        return testUsersList;
    }

    @FXML
    private void updateUser(ActionEvent actionEvent) {
        User selectedUser = empTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            System.out.println("No User Selected");
        }
        else {
            System.out.println("Updating User");
        }
    }

    @FXML
    private void deleteUser(ActionEvent actionEvent) {
        User selectedUser = empTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            System.out.println("No User Selected");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete "+ selectedUser.getUsername() +" from Database?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                testUsersList.remove(selectedUser);

                Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                deleteAlert.setTitle("Delete");
                deleteAlert.setContentText(selectedUser.getUsername() + " has been deleted successfully");
            }
        }
        refresh();
    }
}
