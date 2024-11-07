package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Objects;

public class MovieCard extends CoreController{

    @FXML
    private Label movieNameLabel;

    @FXML
    private ImageView movieCardImage;

    private Movie movie;

    public void setData(Object data){
        movie = (Movie) data;
        movieNameLabel.setText(movie.getmovieName());
        URL imageURL = getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/MovieImages/"+movie.getmovieId()+".jpg");
        if(imageURL == null) {
            imageURL = getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/MovieImages/DefaultMoviePoster.png");
        }
        if(imageURL != null){
            movieCardImage.setImage(new Image(imageURL.toExternalForm()));
        }
    }


    @FXML
    private void showMovieDetails(MouseEvent event) {
        System.out.println(movie.getmovieName());
        showDialogBox("MovieDetails", movie.getmovieName(), movie);
    }
}
