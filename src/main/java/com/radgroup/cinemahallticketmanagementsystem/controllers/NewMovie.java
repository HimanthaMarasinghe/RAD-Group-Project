package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    void addNewMovie(ActionEvent event) {
        String id = newMovieIdField.getText();
        String name = newMovieNameField.getText();
        String duration = newDurationField.getText();
        int price = Integer.parseInt(newTicketPriceField.getText());
        Movie newMovie = new Movie(id, name, duration, price);
        MovieDAO MDAO = new MovieDAOImpl();
        MDAO.addMovie(newMovie);

        Utility.SaveImage(newMovie.getmovieId(), file, "moviePosters");

        dialog.setResult(ButtonType.OK);
        dialog.close();
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