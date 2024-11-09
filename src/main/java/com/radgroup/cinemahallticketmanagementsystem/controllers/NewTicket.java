package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.*;
import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.models.Ticket;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;

import static com.radgroup.cinemahallticketmanagementsystem.App.userName;

public class NewTicket extends dialogBox {

    @FXML
    private ComboBox<String> movieID;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField movieName;

    @FXML
    private VBox seatGrid;

    @FXML
    private TextField seatId;

    @FXML
    private ComboBox<LocalDate> showDate;

    @FXML
    private ComboBox<String> showTime;

    @FXML
    private TextField ticketPrice;

    @FXML
    private TextField cusPhoneField;

    @FXML
    private TextField cusName;

    @FXML
    private DatePicker cusDobField;

    @FXML
    private TextField cusAge;

    // Define rows and seats
    private static final int ROW_COUNT = 10;    // Number of rows
    private static final int SEATS_PER_ROW = 20; // Seats per row

    private Movie selectedMovie;

    private LocalDate SD;
    private String ST;
    private int SID;

    private Boolean[] seatAvailability = new Boolean[200];
    private String previousPhoneInput;
    private boolean newCustomer = false;

    private Label previouslyClicked;

    @FXML
    void addNewTicket(ActionEvent event) {

        if(movieID.getValue() != null && !seatId.getText().isEmpty() && !cusPhoneField.getText().isEmpty()) {
            if(cusPhoneField.getText().length() != 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Phone number must be 10 digits");
                alert.showAndWait();
                return;
            }
            if (newCustomer) {
                if(cusName.getText().isEmpty() || cusDobField.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("This customer phone number is not in database. Please provide customer name and date of birth to add to database");
                    alert.showAndWait();
                    return;
                }
                CustomerDAO CDAO = new CustomerDAOImpl();
                CDAO.addCustomer(new Customer(cusName.getText(), cusPhoneField.getText(), cusDobField.getValue()));
            }

             TicketDAO TDAO = new TicketDAOImpl();
             TDAO.addTicket(new Ticket(SID, cusPhoneField.getText(), userName, seatId.getText()));

             ShowTimeDAO SDAO = new ShowTimeDAOImpl();
             SDAO.updateSeatCount(1,SID);

             dialog.setResult(ButtonType.OK);
             dialog.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Movie Id, Seat Id and Customer phone number can not be empty");
            alert.showAndWait();
        }
    }

    @FXML
    void handleCustomerPhoneInsert(KeyEvent event) {
        String phone = cusPhoneField.getText();
        if (phone.length() > 10 || !phone.matches("\\d*"))
            cusPhoneField.setText(previousPhoneInput);
        else {
            previousPhoneInput = phone;
            if(phone.length() == 10){
                CustomerDAO CDAO = new CustomerDAOImpl();
                Customer customer = CDAO.getCustomer(phone);
                if(customer != null) {
                    cusName.setText(customer.getName() + " (Already registered)");
                    cusDobField.setValue(customer.getDateOfBirth());
                }
                else {
                    cusName.setEditable(true);
                    cusName.setPromptText("Not a registered customer. Add name here");
                    cusDobField.setPromptText("Not a registered customer. Add DoB here");
                    cusDobField.setDisable(false);
                    clearDob();
                    newCustomer = true;
                }
            }else {
                clearDob();
                cusName.clear();
                cusName.setPromptText("Enter customer phone number first");
                cusDobField.setPromptText("Enter customer phone number first");
                cusName.setEditable(false);
                cusDobField.setDisable(true);
                newCustomer = false;
            }
        }
    }


    @FXML
    void calcAgeOnInput(ActionEvent event) {
        Period age = Period.between(cusDobField.getValue(), LocalDate.now());

        int years = age.getYears();
        int months = age.getMonths();
        int days = age.getDays();
        cusAge.setText(years + " years, " + months + " months, " + days + " days");
    }


    private void clearDob() {
        cusDobField.setOnAction(null);
        cusDobField.setValue(null);
        cusAge.setText(null);
        cusDobField.setOnAction(this::calcAgeOnInput);
    }

    @FXML
    void loardSeats(ActionEvent event) {
        seatId.clear();
        ST = showTime.getValue();
        ShowTime showT = new ShowTime(SD, ST, selectedMovie.getmovieId());
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        TicketDAO TDAO = new TicketDAOImpl();
        SID = SDAO.getShowId(showT);
        ArrayList<Ticket> tickets = TDAO.getAllTicketsforShowTime(SID);
        Arrays.fill(seatAvailability, true);
        for (Ticket ticket : tickets) {
            seatAvailability[getSeatIndex(ticket)] = false;
        }
        refreshSeatGrid();
    }

    private int getSeatIndex(Ticket ticket) {
        String seatId = ticket.getSeatNo();
        char row = seatId.charAt(0);
        int seatNumber = Integer.parseInt(seatId.substring(1));
        int rowIndex = row - 'A';
        return (rowIndex * 20) + (seatNumber - 1);
    }

    @FXML
    void selectDate(ActionEvent event) {

        showTime.setOnAction(null);

        showTime.getItems().clear();
        seatId.clear();

        showTime.setOnAction(this::loardSeats);
        clearGrid();

        SD = showDate.getValue();
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        showTime.getItems().clear();
        ArrayList<String> times = SDAO.getAllShowTimes(selectedMovie.getmovieId(), SD);
        for (String time : times) {
            showTime.getItems().add(time);
        }
    }

    @FXML
    void selectMovie(ActionEvent event) {

        showDate.setOnAction(null);
        showTime.setOnAction(null);

        showDate.getSelectionModel().clearSelection();
        showTime.getSelectionModel().clearSelection();

        showDate.setOnAction(this::selectDate);
        showTime.setOnAction(this::loardSeats);

        seatId.clear();
        showTime.getItems().clear();
        showDate.getItems().clear();
        clearGrid();

        String MID = movieID.getValue();
        MovieDAO MDAO = new MovieDAOImpl();
        selectedMovie = MDAO.getMovie(MID);
        movieName.setText(selectedMovie.getmovieName());
        ticketPrice.setText(String.valueOf(selectedMovie.getPrice()));
        movieImage.setImage(Utility.loadImage(MID, "moviePosters"));

        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        ArrayList<ShowTime> ShowTimes = SDAO.listAllShowTimesForMovie(MID);
        for(ShowTime st : ShowTimes) {
            showDate.getItems().add(st.getDate());
        }
    }

    @FXML
    public void initialize() {
        clearGrid();
        MovieDAO MDAO = new MovieDAOImpl();
        movieID.getItems().addAll(MDAO.getAllMovieIds());
    }

    private void refreshSeatGrid(){
        seatGrid.getChildren().clear();
        int i = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
            HBox rowContainer = new HBox(10); // Spacing between seats
            rowContainer.setStyle("-fx-padding: 10;");

            for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW; seatNumber++) {
                String SID = String.format("%c%d", 'A' + row, seatNumber); // Generates seat ID like A1, B1, etc.
                Label seatLabel = createSeatLabel(SID, seatAvailability[i]);

                if(seatAvailability[i++])
                    seatLabel.setOnMouseClicked(event -> handleSeatClick(event, SID));

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

    private void handleSeatClick(MouseEvent event, String SID) {
        seatId.setText(SID);
        if(previouslyClicked != null)
            previouslyClicked.setStyle("-fx-background-color: #0fe000; -fx-text-fill: #000000; -fx-alignment: center; -fx-font-weight: bold;");
        previouslyClicked = (Label) event.getSource();
        previouslyClicked.setStyle("-fx-background-color: #ffe700; -fx-text-fill: #000000; -fx-alignment: center; -fx-font-weight: bold;");
    }

    private void clearGrid() {
        Arrays.fill(seatAvailability, false);
        refreshSeatGrid();
    }

}
