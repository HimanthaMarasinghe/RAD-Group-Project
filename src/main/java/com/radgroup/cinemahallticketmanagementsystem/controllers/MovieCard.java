package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.util.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MovieCard extends CoreController{

    @FXML
    private Label movieNameLabel;

    @FXML
    private ImageView movieCardImage;

    private Movie movie;
    private Movies movieController;

    public void setData(Object data){
        Object[] objectArray = (Object[]) data; // Cast 'data' to an array of objects
        movie = (Movie) objectArray[0];    // Cast the first element to a 'Movie' object
        movieController = (Movies) objectArray[1];
        movieNameLabel.setText(movie.getmovieName());
        movieCardImage.setImage(Utility.loadImage(movie.getmovieId(), "moviePosters"));
    }


    @FXML
    private void showMovieDetails(MouseEvent event) {
        System.out.println(movie.getmovieName());
        Object[] objectArray = {movie, movieController};
        showDialogBox("MovieDetails", movie.getmovieName(), objectArray);
    }
}
