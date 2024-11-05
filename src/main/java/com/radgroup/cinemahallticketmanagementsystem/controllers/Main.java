package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import java.io.IOException;

public class Main implements Cont{
    @FXML
    private Tab CustomersTab;

    @FXML
    private Tab EmployeesTab;

    @FXML
    private Tab HomeTab;

    @FXML
    private Tab MoviesTab;

    @FXML
    private Tab MyProfileTab;

    @FXML
    private Label welcome;


    private Home home;
    private Movies movies;
    private Customers customers;
    private Employees emp;
    private MyProfile myProfile;

    private boolean isInizialized = false;

    public void initialize() {
        System.out.println("Main initialized");
        home = loadTabContent(HomeTab, "Home");
        movies = loadTabContent(MoviesTab, "Movies");
        customers = loadTabContent(CustomersTab, "Customers");
        emp = loadTabContent(EmployeesTab, "Employees");
        myProfile = loadTabContent(MyProfileTab, "My Profile");
        isInizialized = true;

    }

    private <T> T loadTabContent(Tab tab, String fxmlName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/"+fxmlName+".fxml"));
            Node node = fxmlLoader.load();
            tab.setContent(node);
            return fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @FXML
    private void SelectEmpTab(Event event) {
        if(EmployeesTab.isSelected()) {
            System.out.println("Select Employee Tab");
            System.out.println(emp);
            emp.setView(null);
        }
    }

    @FXML
    void SelectCustomersTab(Event event) {
        if(CustomersTab.isSelected()) {
            System.out.println("Select Customer Tab");
            System.out.println(customers);
            customers.setView(null);
        }
    }

    @FXML
    void SelectHomeTab(Event event) {
        if(isInizialized && HomeTab.isSelected()) {
            System.out.println("Select Home Tab");
            System.out.println(home);
            home.setView(null);
        }
    }

    @FXML
    void SelectMoviesTab(Event event) {
        if(MoviesTab.isSelected()) {
            System.out.println("Select Movie Tab");
            System.out.println(movies);
            movies.setView(null);
        }
    }

    @FXML
    void SelectMyProfileTab(Event event) {
        if(MyProfileTab.isSelected()) {
            System.out.println("Select My Profile Tab");
            System.out.println(myProfile);
            myProfile.setView(null);
        }
    }

    @Override
    public void setView(Object data) {
        if (data instanceof String) {
            welcome.setText("Welcome "+(String) data); // Set the username on the label
        }
    }
}
