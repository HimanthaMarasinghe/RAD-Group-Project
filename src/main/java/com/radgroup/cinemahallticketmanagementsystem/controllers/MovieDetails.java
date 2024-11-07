package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
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
    private TableColumn<ShowTime, LocalDate> showTimeDateCol;

    @FXML
    private TableColumn<ShowTime, Integer> showTimeSeatCol;

    @FXML
    private TableView<ShowTime> showTimeTable;

    @FXML
    private TableColumn<ShowTime, String> showTimeTimeCol;

    @FXML
    private TextField ticketPriceField;

    private Movie selectedMovie;
    private Movies movieController;

    private ObservableList<ShowTime> showTimeList;

    public void initialize() {
        showTimeDateCol.setCellValueFactory(new PropertyValueFactory<ShowTime, LocalDate>("date"));
        showTimeTimeCol.setCellValueFactory(new PropertyValueFactory<ShowTime, String>("timeslot"));
        showTimeSeatCol.setCellValueFactory(new PropertyValueFactory<ShowTime, Integer>("seats"));

        showTimeList = FXCollections.observableArrayList();
        showTimeTable.setItems(showTimeList);
    }

    @FXML
    private void handleAddShowTimes(ActionEvent event) {
        showDialogBox("NewShowTime", "Add Show Time", selectedMovie);
        refresh();
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
        refresh();
    }

    private void refresh(){
        ShowTimeDAO SDAO = new ShowTimeDAOImpl();
        showTimeList.clear();
        showTimeList.addAll(SDAO.listAllShowTimesForMovie(selectedMovie.getmovieId()));
    }

    @Override
    public void setDialogBox(Object dataObject) {
        Object[] objectArray = (Object[]) dataObject;

        selectedMovie = (Movie) objectArray[0];
        movieController = (Movies) objectArray[1];
        movieIdField.setText(selectedMovie.getmovieId());
        movieNameField.setText(selectedMovie.getmovieName());
        durationField.setText(selectedMovie.getDuration());
        ticketPriceField.setText(String.valueOf(selectedMovie.getPrice()));
        movieCardImage.setImage(Utility.loadImage(selectedMovie.getmovieId(), "moviePosters"));
        refresh();
    }

    @FXML
    private void updateST(ActionEvent actionEvent) {
        ShowTime selectedST = showTimeTable.getSelectionModel().getSelectedItem();
        if (selectedST != null) {
            showDialogBox("UpdateShowTime", "Update Show Time", selectedST);
            refresh();
        }
    }

    @FXML
    private void deleteST(ActionEvent actionEvent) {
        ShowTime selectedST = showTimeTable.getSelectionModel().getSelectedItem();
        if (selectedST != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete "+ selectedST.getDate() + ", " + selectedST.getTimeslot() + " showTime?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                ShowTimeDAO SDAO = new ShowTimeDAOImpl();
                boolean deleted = SDAO.deleteShowTime(selectedST.getShowid());

                if (deleted) {
                    Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                    deleteAlert.setTitle("Delete");
                    deleteAlert.setContentText(selectedST.getDate() + ", " + selectedST.getTimeslot() + " Showtime has been deleted successfully");
                    deleteAlert.show();
                }else{
                    Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
                    deleteAlert.setTitle("Error");
                    deleteAlert.setHeaderText("Delete Failed");
                    deleteAlert.setContentText("If there are tickets book for this show time, it cannot be deleted. Please, delete those tickets, or update there show time to another show time and try again.");
                    deleteAlert.show();
                }
            }
        }
        refresh();
    }
}
