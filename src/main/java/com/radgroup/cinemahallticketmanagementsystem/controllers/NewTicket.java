package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class NewTicket {

    @FXML
    private ComboBox<?> movieID;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField movieNmae;

    @FXML
    private VBox seatGrid;

    @FXML
    private TextField seatId;

    @FXML
    private ComboBox<?> showDate;

    @FXML
    private ComboBox<?> showTime;

    @FXML
    private TextField ticketPrice;

    @FXML
    void addNewTicket(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void handleCustomerPhoneInsert(KeyEvent event) {

    }

    @FXML
    void selectDate(ActionEvent event) {

    }

    @FXML
    void selectMovie(ActionEvent event) {

    }

}
