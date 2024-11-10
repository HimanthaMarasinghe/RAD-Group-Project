package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.*;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.models.Ticket;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowTimeDetails extends dialogBox {

    @FXML
    private TextField movieIdField;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField movieNameField;

    @FXML
    private VBox seatGrid;

    @FXML
    private TextField showTimeDate;

    @FXML
    private TextField showTimeTimeField;

    private Boolean[] seatAvailability = new Boolean[200];
    private static final int ROW_COUNT = 10;    // Number of rows
    private static final int SEATS_PER_ROW = 20; // Seats per row
    private ShowTime ST;
    private ArrayList<Ticket> tickets;

    public void setDialogBox(Object data){
        ST = (ShowTime) data;
//        MovieDAO MDAO = new MovieDAOImpl();
//        Movie movie = MDAO.getMovie(ST.getMovieid());
//
//        movieIdField.setText(ST.getMovieid());
//        movieNameField.setText(movie.getmovieName());
//        showTimeDate.setText(String.valueOf(ST.getDate()));
//        showTimeTimeField.setText(ST.getTimeslot());
//        movieImage.setImage(Utility.loadImage(ST.getMovieid(), "moviePosters"));
//
//        refreshSeatGrid();


        setDialogBox();
    }

    public void setDialogBox(){
        MovieDAO MDAO = new MovieDAOImpl();
        Movie movie = MDAO.getMovie(ST.getMovieid());

        movieIdField.setText(ST.getMovieid());
        movieNameField.setText(movie.getmovieName());
        showTimeDate.setText(String.valueOf(ST.getDate()));
        showTimeTimeField.setText(ST.getTimeslot());
        movieImage.setImage(Utility.loadImage(ST.getMovieid(), "moviePosters"));

        refreshSeatGrid();
    }

    private int getSeatIndex(Ticket ticket) {
        String seatId = ticket.getSeatNo();
        char row = seatId.charAt(0);
        int seatNumber = Integer.parseInt(seatId.substring(1));
        int rowIndex = row - 'A';
        return (rowIndex * 20) + (seatNumber - 1);
    }

    public void refreshSeatGrid(){
        TicketDAO TDAO = new TicketDAOImpl();
        tickets = TDAO.getAllTicketsforShowTime(ST.getShowid());
        Arrays.fill(seatAvailability, true);
        for (Ticket ticket : tickets) {
            seatAvailability[getSeatIndex(ticket)] = false;
        }
        seatGrid.getChildren().clear();
        int i = 0;
        int t = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
            HBox rowContainer = new HBox(10); // Spacing between seats
            rowContainer.setStyle("-fx-padding: 10;");

            for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW; seatNumber++) {
                String SID = String.format("%c%d", 'A' + row, seatNumber); // Generates seat ID like A1, B1, etc.
                Label seatLabel = createSeatLabel(SID, seatAvailability[i]);

                if(!seatAvailability[i++]) {
                    Object[] objectArray = {tickets.get(t++), this};
                    seatLabel.setOnMouseClicked(event -> handleSeatClick(event, objectArray));
                }

                rowContainer.getChildren().add(seatLabel);
            }

            seatGrid.getChildren().add(rowContainer);
        }
    }

    private Label createSeatLabel(String seatId, boolean available) {
        Label label = new Label(seatId);
        label.setPrefSize(37, 20);
        if (available) {
            label.setStyle("-fx-background-color: #0fe000; -fx-text-fill: #000000; -fx-alignment: center; -fx-font-weight: bold;");
        }else{
            label.setStyle("-fx-background-color: #ae1c00; -fx-text-fill: white; -fx-alignment: center; -fx-font-weight: bold;");
        }
        return label;
    }

    private void handleSeatClick(MouseEvent event, Object data) {
//        System.out.println(SID);
        showDialogBox("Ticket", "Ticket Details", data);
    }

}