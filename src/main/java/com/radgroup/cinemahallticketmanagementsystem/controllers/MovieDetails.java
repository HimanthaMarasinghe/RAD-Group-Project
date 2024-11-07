package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.time.LocalTime;
import java.util.Optional;

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

    private Movie selectedMovie;
    private Movies movieController;

    @FXML
    private void handleAddShowTimes(ActionEvent event) {

    }

    @FXML
    private void handleMovieDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Movie");
        alert.setHeaderText("Are you sure you want to delete "+selectedMovie.getmovieName()+" ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            MovieDAO movieDAO = new MovieDAOImpl();
            movieDAO.deleteMovie(selectedMovie.getmovieId());

            Utility.deleteImage(selectedMovie.getmovieId(), "moviePosters");

            Alert deletedAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedAlert.setTitle("Success");
            deletedAlert.setHeaderText("Movie Deleted");
            deletedAlert.setContentText("Movie "+selectedMovie.getmovieName()+" successfully deleted");
            deletedAlert.show();
            movieController.movieListRefresh();
        }
        dialog.close();
    }

    @FXML
    private void handleMovieUpdate(ActionEvent event) {
        Object[] objectsToPass = {selectedMovie, movieController, this};
        showDialogBox("UpdateMovie", "Update Movie Details", objectsToPass);
        movieController.movieListRefresh();
    }

    @FXML
    private void refresh(ActionEvent event) {

    }

    @Override
    public void setDialogBox(Object dataObject) {
        Object[] objectArray = (Object[]) dataObject;

        selectedMovie = (Movie) objectArray[0];
        movieController = (Movies) objectArray[1];
//        ShowTimeDAO showTimeDAO = new ShowTimeDAOImpl();
        movieIdField.setText(selectedMovie.getmovieId());
        movieNameField.setText(selectedMovie.getmovieName());
        durationField.setText(selectedMovie.getDuration());
        ticketPriceField.setText(String.valueOf(selectedMovie.getPrice()));
        movieCardImage.setImage(Utility.loadImage(selectedMovie.getmovieId(), "moviePosters"));
    }
}
