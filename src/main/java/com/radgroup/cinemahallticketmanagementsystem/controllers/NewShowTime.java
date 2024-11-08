package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NewShowTime extends dialogBox{

    @FXML
    private DatePicker fromDate;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField movieName;

    @FXML
    private ComboBox<String> timeSlot;

    @FXML
    private DatePicker toDate;

    private String movieID;

    @FXML
    void addTShowTime(ActionEvent event) {
        String TimeSlot = timeSlot.getSelectionModel().getSelectedItem();
        LocalDate DateFrom = fromDate.getValue();
        LocalDate DateTo = toDate.getValue();
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();

        int noOfOverlapingSTs = SDAO.getNumberOfShowTimes(DateFrom, DateTo, TimeSlot);

        if(noOfOverlapingSTs == 0) {
            for (long days = ChronoUnit.DAYS.between(DateFrom, DateTo); days >= 0; days--) {
                LocalDate currentDate = DateFrom.plusDays(days);
                SDAO.addShowTime(new ShowTime(currentDate, TimeSlot, movieID));
            }
            dialog.setResult(ButtonType.OK);
            dialog.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Overlapping Show Times");
            alert.setContentText(noOfOverlapingSTs + " show times you're trying to add overlap with existing entries in the database for the selected dates and time slots. Please review current show times from Upcoming table in the Home tab, and ensure that new show times do not overlap with them before adding.");
            alert.showAndWait();
        }
    }

    @Override
    public void setDialogBox(Object dataObject) {
        Movie movie = (Movie) dataObject;
        movieName.setText(movie.getmovieName());
        movieImage.setImage(Utility.loadImage(movie.getmovieId(), "moviePosters"));
        movieID = movie.getmovieId();
        timeSlot.getItems().addAll("10:30 to 12:30", "13:30 to 15:30", "16:30 to 18:30", "20:00 to 22:00");
    }
}
