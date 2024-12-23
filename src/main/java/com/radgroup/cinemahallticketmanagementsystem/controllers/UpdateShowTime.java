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
import java.util.ArrayList;

public class UpdateShowTime extends dialogBox {

    @FXML
    private ComboBox<String> movieID;

    @FXML
    private ComboBox<String> timeSlot;

    @FXML
    private DatePicker date;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField movieName;

    @FXML
    void selectMovie(ActionEvent event) {
        MovieDAO movieDAO = new MovieDAOImpl();
        Movie movie = movieDAO.getMovie(movieID.getValue());
        if (movie != null) {
            movieName.setText(movie.getmovieName());
            movieImage.setImage(Utility.loadImage(movie.getmovieId(), "moviePosters"));
        }
    }

    private ShowTime showTime;

    @FXML
    private void updateShowTime(ActionEvent event) {
        String Id = movieID.getSelectionModel().getSelectedItem();
        String TimeSlot = timeSlot.getSelectionModel().getSelectedItem();
        LocalDate Date = date.getValue();



        ShowTime newST = new ShowTime(showTime.getShowid(), Date,TimeSlot, Id);
        if(!showTime.areAttributesEqual(newST)) {
            ShowTimeDAO SDAO = new ShowTimeDAOImpl();
            int noOfOverlapingSTs = SDAO.getNumberOfShowTimes(Date, Date, TimeSlot);
            if (noOfOverlapingSTs == 0) {
                SDAO.updateShowTime(newST);
                dialog.setResult(ButtonType.OK);
                dialog.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Overlapping Show Times");
                alert.setContentText("The show time you're trying to add overlap with existing entries in the database for the selected date and time slot. Please review current show times from Upcoming table in the Home tab, and ensure that new show time do not overlap with them before adding.");
                alert.showAndWait();
            }
        }
    }

    public void setDialogBox(Object object) {
        showTime = (ShowTime) object;

        MovieDAO MDAO = new MovieDAOImpl();
        movieID.getItems().addAll(MDAO.getAllMovieIds());
        timeSlot.getItems().addAll("10:30 to 12:30", "13:30 to 15:30", "16:30 to 18:30", "20:00 to 22:00");

        String selectedMovieId = showTime.getMovieid();
        movieID.setValue(selectedMovieId);
        date.setValue(showTime.getDate());
        timeSlot.setValue(showTime.getTimeslot());
        movieImage.setImage(Utility.loadImage(showTime.getMovieid(), "moviePosters"));

        movieName.setText((MDAO.getMovie(selectedMovieId)).getmovieName());
    }
}
