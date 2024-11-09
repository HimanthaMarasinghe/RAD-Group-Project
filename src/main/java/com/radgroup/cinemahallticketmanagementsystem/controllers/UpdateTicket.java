package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.*;
import com.radgroup.cinemahallticketmanagementsystem.models.*;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;

import static com.radgroup.cinemahallticketmanagementsystem.App.userName;

public class UpdateTicket extends dialogBox {

    @FXML
    private TextField cusAge;

    @FXML
    private TextField cusName;

    @FXML
    private TextField cusPhoneField;

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
    private TextField employeeName;

    private final Boolean[] seatAvailability = new Boolean[200];
    private static final int ROW_COUNT = 10;    // Number of rows
    private static final int SEATS_PER_ROW = 20; // Seats per row
    private Ticket ticket;
    private Ticket preTicket;

    /**
     * Used to refresh the Ticket details window.
     */
    private TicketCon ticketController;

    /**
     * Used to refresh the ShowTime Details window.
     */
    private ShowTimeDetails stdControler;

    private Movie selectedMovie;

    /**
     * Show Date
     */
    private LocalDate SD;

    /**
     * Show Time
     */
    private String ST;

    /**
     * ShowTime ID
     */
    private int SID;
    private int preSID;

    private String customerPhone;

    private Label previouslyClicked;

    @FXML
    void loardSeats(ActionEvent event) {
        ST = showTime.getValue();
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        TicketDAO TDAO = new TicketDAOImpl();
        ShowTime showT = new ShowTime(SD, ST, selectedMovie.getmovieId());
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

    public void setDialogBox(Object data) {
        Object[] objectArray = (Object[]) data;
        ticket = (Ticket) objectArray[0];
        preTicket = (Ticket) objectArray[0];
        stdControler = (ShowTimeDetails) objectArray[1];
        ticketController = (TicketCon) objectArray[2];

//        ticket = (Ticket) data;

        MovieDAO MDAO = new MovieDAOImpl();
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        CustomerDAO CDAO = new CustomerDAOImpl();
        UserDAO UDAO = new UserDAOImpl();

        SID = ticket.getShowTimeId();
        preSID = ticket.getShowTimeId();
        ShowTime showtime = SDAO.getShowTime(SID);
        selectedMovie = MDAO.getMovie(showtime.getMovieid());
        Customer customer = CDAO.getCustomer(ticket.getCustomerPhone());
        User user = UDAO.getUser(ticket.getEmpUsername());

        SD = showtime.getDate();
        ST = showtime.getTimeslot();
        SID = showtime.getShowid();
        customerPhone = customer.getPhone();

        movieID.getItems().clear();
        movieID.getItems().addAll(MDAO.getAllMovieIds());
        movieID.setValue(selectedMovie.getmovieId());

        movieName.setText(selectedMovie.getmovieName());
        seatId.setText(ticket.getSeatNo());

        showDate.getItems().clear();
        ArrayList<ShowTime> showTimes = SDAO.listAllShowTimesForMovie(selectedMovie.getmovieId());
        for (ShowTime st : showTimes) {
            showDate.getItems().add(st.getDate());
        }
        showDate.setValue(showtime.getDate());

        showTime.getItems().clear();
        showTime.getItems().addAll(SDAO.getAllShowTimes(selectedMovie.getmovieId(), showtime.getDate()));
        showTime.setValue(ST);

        seatId.setText(ticket.getSeatNo());
        ticketPrice.setText(String.valueOf(selectedMovie.getPrice()));

        cusName.setText(customer.getName());
        cusPhoneField.setText(customer.getPhone());


        Period age = Period.between(customer.getDateOfBirth(), LocalDate.now());

        int years = age.getYears();
        int months = age.getMonths();
        int days = age.getDays();


        cusAge.setText( years + " years, " + months + " months, " + days + " days");

        employeeName.setText(user.getName());
        movieImage.setImage(Utility.loadImage(selectedMovie.getmovieId(), "moviePosters"));
        loardSeats(null);
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

        showDate.getItems().clear();
        showTime.getItems().clear();
        seatId.clear();
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
    void updateTicket(ActionEvent event) {

        System.out.println(movieID.getValue());
        System.out.println(showDate.getValue());
        System.out.println(showTime.getValue());
        System.out.println(seatId);


        if(movieID.getValue() != null && showDate.getValue() != null && showTime.getValue() != null && !seatId.getText().isEmpty()) {

            TicketDAO TDAO = new TicketDAOImpl();
            ShowTime showT = new ShowTime(showDate.getValue(), showTime.getValue(), movieID.getValue());


            ShowTimeDAO SDAO = new ShowTimeDAOImpl();
//            showT.setShowid(SDAO.getShowId(showT));
            Ticket updatedTicket = new Ticket(ticket.getTicketId(), SDAO.getShowId(showT), customerPhone, userName, seatId.getText());
            if(preTicket.areAttributesEqual(updatedTicket)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No new Data");
                alert.setContentText("No new data provide");
                alert.showAndWait();
                return;
            }
//            Ticket updatedTicket = new Ticket(ticket.getTicketId(), showT.getShowid(), customerPhone, userName, seatId.getText());
            TDAO.updateTicket(updatedTicket);
            if(SID != preSID){
                SDAO.updateSeatCount(0, preSID); //Increase seat availability
                SDAO.updateSeatCount(1, SID);    //Decrease seat availability
            }
            Object[] objectsToPass = {updatedTicket, stdControler};
            ticketController.setDialogBox(objectsToPass);


            stdControler.setDialogBox();


            dialog.setResult(ButtonType.OK);
            dialog.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Movie ID, Date, Time, and Seat ID can not be empty");
            alert.showAndWait();
        }
    }

    private void clearGrid() {
        Arrays.fill(seatAvailability, false);
        refreshSeatGrid();
    }
}
