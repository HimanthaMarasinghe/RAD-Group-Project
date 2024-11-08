package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class NewMovie extends dialogBox {

    @FXML
    private ImageView movieCardImage;

    @FXML
    private TextField newDurationField;

    @FXML
    private TextField newMovieIdField;

    @FXML
    private TextField newMovieNameField;

    @FXML
    private TextField newTicketPriceField;

    private java.io.File file;
    private boolean movieIdExist;

    private String previousPriceInput = "0";

    /**
     * Making sure price field only accept integer values.
     * @param event
     */
    @FXML
    private void filterPriceInput(KeyEvent event) {
        if(newTicketPriceField.getText().matches("\\d*")) {
            previousPriceInput = newTicketPriceField.getText();
        }else
            newTicketPriceField.setText(previousPriceInput);
    }


    @FXML
    private void handleTextInput(KeyEvent event) {
        String inputText = newMovieIdField.getText();
        if (inputText.length() > 5) {
            newMovieIdField.setText(inputText.substring(0, 5));
        }

        MovieDAO MDAO = new MovieDAOImpl();
        Movie movieWithSameID = MDAO.getMovie(inputText);
        movieIdExist = movieWithSameID != null;
    }

    @FXML
    private void trimDuration(KeyEvent event) {
        String inputText = newDurationField.getText();
        if (inputText.length() > 10) {
            newDurationField.setText(inputText.substring(0, 10));
        }
    }

    @FXML
    void addNewMovie(ActionEvent event) {
        String id = newMovieIdField.getText();
        String name = newMovieNameField.getText();
        String duration = newDurationField.getText();
        String price = newTicketPriceField.getText();

        if(id.trim().isEmpty() || name.trim().isEmpty() || duration.trim().isEmpty() || price.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Input fields cannot be empty");
            alert.showAndWait();
        }else if (movieIdExist){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Movie ID already exist");
            alert.showAndWait();
        }else{
            int priceInt = Integer.parseInt(price);
            Movie newMovie = new Movie(id, name, duration, priceInt);
            MovieDAO MDAO = new MovieDAOImpl();
            MDAO.addMovie(newMovie);

            if(file != null)
                Utility.SaveImage(newMovie.getmovieId(), file, "moviePosters");

            dialog.setResult(ButtonType.OK);
            dialog.close();
        }
    }

    @FXML
    void handleSelectImage(ActionEvent event) {

        file = Utility.selectImage(event);

        if (file != null) {
            // Load and display the selected image
            Image image = new Image(file.toURI().toString());
            movieCardImage.setImage(image);
        }
    }
}