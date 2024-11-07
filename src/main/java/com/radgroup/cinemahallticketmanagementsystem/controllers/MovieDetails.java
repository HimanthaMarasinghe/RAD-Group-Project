package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalTime;

public class MovieDetails extends dialogBox {
    @FXML
    private TextField durationField;

    @FXML
    private ImageView movieCardImage;

    @FXML
    private TextField movieIdField;

    @FXML
    private TextField movieNameField;

    @FXML
    private TableColumn<ShowTime, LocalTime> showTimeDateCol;

    @FXML
    private TableColumn<ShowTime, String> showTimeSeatCol;

    @FXML
    private TableView<ShowTime> showTimeTable;

    @FXML
    private TableColumn<ShowTime, String> showTimeTimeCol;

    @FXML
    private TextField ticketPriceField;

    @FXML
    private void handleAddShowTimes(ActionEvent event) {

    }

    @FXML
    private void handleMovieDelete(ActionEvent event) {

    }

    @FXML
    private void handleMovieUpdate(ActionEvent event) {

    }

    @FXML
    private void refresh(ActionEvent event) {

    }

    @Override
    public void setDialogBox(Object dataObject) {
        Movie selectedMovie = (Movie) dataObject;
//        ShowTimeDAO showTimeDAO = new ShowTimeDAOImpl();
        movieIdField.setText(selectedMovie.getmovieId());
        movieNameField.setText(selectedMovie.getmovieName());
        durationField.setText(selectedMovie.getDuration());
        ticketPriceField.setText(String.valueOf(selectedMovie.getPrice()));
        URL imageURL = getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/MovieImages/"+selectedMovie.getmovieId()+".jpg");
        if(imageURL == null) {
            imageURL = getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/MovieImages/DefaultMoviePoster.png");
        }
        if(imageURL != null){
            movieCardImage.setImage(new Image(imageURL.toExternalForm()));
        }
    }
}
