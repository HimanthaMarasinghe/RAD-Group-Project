package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.*;
import com.radgroup.cinemahallticketmanagementsystem.models.*;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.Optional;

public class TicketCon extends dialogBox{


    @FXML
    private TextField employeeName;

    @FXML
    private TextField cusName;

    @FXML
    private TextField cusPhoneField;

    @FXML
    private TextField movieId;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField movieName;

    @FXML
    private TextField seatId;

    @FXML
    private TextField showDate;

    @FXML
    private TextField showTime;

    @FXML
    private TextField ticketPrice;

    private Ticket ticket;
    private ShowTimeDetails stdController;

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Ticket");
        alert.setContentText("Are you sure you want to delete this ticket?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            TicketDAO TDAO = new TicketDAOImpl();
            TDAO.deleteTicket(ticket.getTicketId());
            stdController.refreshSeatGrid();
            dialog.close();
        }
    }

    @FXML
    void handleCustomerPhoneInsert(KeyEvent event) {

    }

    @FXML
    void updateTicket(ActionEvent event) {
//        Object[] objectArray = {ticket, stdController, this};
//        showDialogBox("UpdateTicket", "Update Ticket", objectArray);
        showDialogBox("UpdateTicket", "Update Ticket", ticket);

    }

    public void setDialogBox(Object data){
        Object[] objectArray = (Object[]) data;
        ticket = (Ticket) objectArray[0];
        stdController = (ShowTimeDetails) objectArray[1];

        MovieDAO MDAO = new MovieDAOImpl();
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        CustomerDAO CDAO = new CustomerDAOImpl();
        UserDAO UDAO = new UserDAOImpl();

        ShowTime showtime = SDAO.getShowTime(ticket.getShowTimeId());
        Movie movie = MDAO.getMovie(showtime.getMovieid());
        Customer customer = CDAO.getCustomer(ticket.getCustomerPhone());
        User user = UDAO.getUser(ticket.getEmpUsername());

        employeeName.setText(user.getName());
        cusName.setText(customer.getName());
        cusPhoneField.setText(customer.getPhone());
        movieId.setText(movie.getmovieId());
        movieName.setText(movie.getmovieName());
        seatId.setText(ticket.getSeatNo());
        showDate.setText(String.valueOf(showtime.getDate()));
        showTime.setText(showtime.getTimeslot());
        ticketPrice.setText(String.valueOf(movie.getPrice()));
        movieImage.setImage(Utility.loadImage(movie.getmovieId(), "moviePosters"));
    }

}
