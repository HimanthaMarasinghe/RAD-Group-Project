package com.radgroup.cinemahallticketmanagementsystem.controllers;

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

    private Upcoming u1 = new Upcoming("aa", "bb", "cc", "dd");
    private Upcoming u2 = new Upcoming("aa", "bb", "cc", "dd");
    private Upcoming u3 = new Upcoming("aa", "bb", "cc", "dd");

    public void initialize() {
        System.out.println("Home Initializes");

        date.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("time"));
        movie.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("movie"));
        seat.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("seat"));
        Up = FXCollections.observableArrayList(u1, u2, u3);
        UpcomingTable.setItems(Up);
        test();
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

    private void test(){
        for (int i = 0; i < 10; i++) {
            Up.add(new Upcoming("Test","Test","Test","Test"));
        }
    }
}
