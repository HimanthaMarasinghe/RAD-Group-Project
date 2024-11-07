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

        boolean errorOcured = false;

        for (long days = ChronoUnit.DAYS.between(DateFrom, DateTo); days >= 0; days--) {
            LocalDate currentDate = DateFrom.plusDays(days);
            boolean success = SDAO.addShowTime(new ShowTime(currentDate, TimeSlot, movieID));
            if (!success) {
                errorOcured = true;
            }
        }
        if (errorOcured) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Some show times did not get added to the database. Showtime with same date and time being present in the database may cause the error.");
        }
        dialog.setResult(ButtonType.OK);
        dialog.close();
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
