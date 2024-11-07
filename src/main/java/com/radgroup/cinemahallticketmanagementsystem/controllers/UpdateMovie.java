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

public class UpdateMovie extends dialogBox {

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

    private Movie currentMovie;
    private Movies moviesController;
    private MovieDetails movieDetailsController;
    private java.io.File file;

    @FXML
    private void updateMovie(ActionEvent event) {
        String id = newMovieIdField.getText();
        String name = newMovieNameField.getText();
        String duration = newDurationField.getText();
        int price = Integer.parseInt(newTicketPriceField.getText());
        Movie updatedMovie = new Movie(id,name,duration,price);

        boolean refreshRequired = false;

        //Movie will be updated only if the user provide new details.
        if(!updatedMovie.areAttributesEqual(currentMovie)){
            MovieDAO MDAO = new MovieDAOImpl();
            MDAO.updateMovie(updatedMovie, currentMovie.getmovieId());

            System.out.println("Movie updated");

            refreshRequired = true;
        }

        //Update Image
        if(file != null){
            Utility.deleteImage(currentMovie.getmovieId(), "moviePosters");
            Utility.SaveImage(updatedMovie.getmovieId(), file, "moviePosters");

            System.out.println("Movie image updated");

            refreshRequired = true;
        }

        if(refreshRequired){
            Object[] objectsToPass = {updatedMovie, moviesController};

            //Refresh Movie Details DialogBox
            movieDetailsController.setDialogBox(objectsToPass);

            //Refresh Movies Tab
            moviesController.movieListRefresh();
        }


        dialog.setResult(ButtonType.OK);
        dialog.close();
    }

    @FXML
    private void handleChangeImage(ActionEvent event) {

        file = Utility.selectImage(event);

        if (file != null) {
            // Load and display the selected image
            Image image = new Image(file.toURI().toString());
            movieCardImage.setImage(image);
        }
        System.out.println("New image selected");
    }

    public void setDialogBox(Object data){
        Object[] objectArray = (Object[]) data;
        currentMovie = (Movie) objectArray[0];
        moviesController = (Movies) objectArray[1];
        movieDetailsController = (MovieDetails) objectArray[2];

        newMovieIdField.setText(currentMovie.getmovieId());
        newMovieNameField.setText(currentMovie.getmovieName());
        newDurationField.setText(currentMovie.getDuration());
        newTicketPriceField.setText(String.valueOf(currentMovie.getPrice()));

        movieCardImage.setImage(Utility.loadImage(currentMovie.getmovieId(), "moviePosters"));
    }
}
