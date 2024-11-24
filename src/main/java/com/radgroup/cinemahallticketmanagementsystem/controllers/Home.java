package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.models.Upcoming;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class Home extends CoreController implements Cont{

    @FXML
    private TableColumn<Upcoming, LocalDate> date;

    @FXML
    private TableColumn<Upcoming, String> movie;

    @FXML
    private TableColumn<Upcoming, String> seat;

    @FXML
    private TableColumn<Upcoming, String> time;

    @FXML
    private TableView<Upcoming> UpcomingTable;

    private ObservableList<Upcoming> Up = FXCollections.observableArrayList();

    public void initialize() {
        System.out.println("Home Initializes");
//        ShowTimeDAO SDAO = new ShowTimeDAOImpl();

        date.setCellValueFactory(new PropertyValueFactory<Upcoming, LocalDate>("date"));
        time.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("timeslot"));
        movie.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("movie"));
        seat.setCellValueFactory(new PropertyValueFactory<Upcoming, String>("seats"));
//        Up = FXCollections.observableArrayList();
//        Up.addAll(SDAO.listAllUpcomingShowTimes());
        refresh();
        UpcomingTable.setItems(Up);
    }


    @FXML
    void showTickets(ActionEvent event) {
        Upcoming u = UpcomingTable.getSelectionModel().getSelectedItem();

        if (u == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Selection");
            alert.setContentText("Please select a Show Time first");
            alert.showAndWait();
        }else {
            ShowTime selectedST = new ShowTime(u.getShowid(), u.getDate(), u.getTimeslot(), u.getMovieid());
            showDialogBox("ShowTimeDetails", "Showtime Details", selectedST);
            refresh();
        }
    }

    @Override
    public void setView(Object data) {
        System.out.println("Set View method called from Home controller");
        refresh();
    }

    private void refresh() {
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        Up.clear();
        Up.addAll(SDAO.listAllUpcomingShowTimes());
        System.out.println("Upcoming Show List refreshed");
    }

    @FXML
    private void AddNewTicket(ActionEvent event) {
        System.out.println("AddNewTicket method called from Home controller");
        showDialogBox("NewTicket", "New Ticket");
        refresh();
    }
}
