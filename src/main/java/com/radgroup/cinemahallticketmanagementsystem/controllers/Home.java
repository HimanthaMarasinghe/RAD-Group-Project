package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Upcoming;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Home extends CoreController implements Cont{

    @FXML
    private TableColumn<Upcoming, String> date;

    @FXML
    private TableColumn<Upcoming, String> movie;

    @FXML
    private TableColumn<Upcoming, String> seat;

    @FXML
    private TableColumn<Upcoming, String> time;

    @FXML
    private TableView<Upcoming> UpcomingTable;

    private ObservableList<Upcoming> Up;

    public void initialize() {
        System.out.println("Home Initializes");
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();

        date.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("time"));
        movie.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("movie"));
        seat.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("seat"));
        Up = FXCollections.observableArrayList();
        Up.addAll(SDAO.listAllUpcomingShowTimes());
        UpcomingTable.setItems(Up);
    }

    @Override
    public void setView(Object data) {
        System.out.println("Set View method called from Home controller");
    }

    @FXML
    private void AddNewTicket(ActionEvent event) {
        System.out.println("AddNewTicket method called from Home controller");
        showDialogBox("NewTicket", "New Ticket");
    }
}
