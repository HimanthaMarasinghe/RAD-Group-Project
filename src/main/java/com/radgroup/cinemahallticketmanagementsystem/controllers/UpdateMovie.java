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
    private boolean movieIdExist;

    private String previousPriceInput;

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
        movieIdExist = movieWithSameID != null && !movieWithSameID.areAttributesEqual(currentMovie);
    }

    @FXML
    private void trimDuration(KeyEvent event) {
        String inputText = newDurationField.getText();
        if (inputText.length() > 10) {
            newDurationField.setText(inputText.substring(0, 10));
        }
    }


    @FXML
    private void updateMovie(ActionEvent event) {
        String id = newMovieIdField.getText();
        String name = newMovieNameField.getText();
        String duration = newDurationField.getText();
        String price = newTicketPriceField.getText();

        if(id.trim().isEmpty() || name.trim().isEmpty() || duration.trim().isEmpty() || price.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Input fields cannot be empty");
            alert.showAndWait();
        }else {
            int priceInt = Integer.parseInt(price);
            Movie updatedMovie = new Movie(id,name,duration,priceInt);

            boolean refreshRequired = false;


            //Movie will be updated only if the user provide new details.
            if (!updatedMovie.areAttributesEqual(currentMovie)) {
                if (movieIdExist) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Movie ID already exist");
                    alert.show();
                    return;
                } else {
                    MovieDAO MDAO = new MovieDAOImpl();
                    MDAO.updateMovie(updatedMovie, currentMovie.getmovieId());

                    System.out.println("Movie updated");

                    refreshRequired = true;
                }
            }

            //Update Image
            if (file != null) {
                Utility.deleteImage(currentMovie.getmovieId(), "moviePosters");
                Utility.SaveImage(updatedMovie.getmovieId(), file, "moviePosters");

                System.out.println("Movie image updated");

                refreshRequired = true;
            }

            if (refreshRequired) {
                Object[] objectsToPass = {updatedMovie, moviesController};

                //Refresh Movie Details DialogBox
                movieDetailsController.setDialogBox(objectsToPass);

                //Refresh Movies Tab
                moviesController.movieListRefresh();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No new Data");
                alert.setContentText("Movie details were not updated because no new data was provided.");
                alert.show();
            }

            dialog.setResult(ButtonType.OK);
            dialog.close();
//
//
//
//            //Movie will be updated only if the user provide new details.
//            if (!updatedMovie.areAttributesEqual(currentMovie)) {
//                if (movieIdExist) {
//                    midErrorOccurred = true;
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error");
//                    alert.setContentText("Movie ID already exist");
//                    alert.showAndWait();
//                } else {
//                    MovieDAO MDAO = new MovieDAOImpl();
//                    MDAO.updateMovie(updatedMovie, currentMovie.getmovieId());
//
//                    System.out.println("Movie updated");
//
//                    refreshRequired = true;
//                }
//            }
//
//            //Update Image
//            if (file != null && !midErrorOccurred) {
//                Utility.deleteImage(currentMovie.getmovieId(), "moviePosters");
//                Utility.SaveImage(updatedMovie.getmovieId(), file, "moviePosters");
//
//                System.out.println("Movie image updated");
//
//                refreshRequired = true;
//            }
//
//            if (refreshRequired) {
//                Object[] objectsToPass = {updatedMovie, moviesController};
//
//                //Refresh Movie Details DialogBox
//                movieDetailsController.setDialogBox(objectsToPass);
//
//                //Refresh Movies Tab
//                moviesController.movieListRefresh();
//            }
//            else{
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("No new Data");
//                alert.setContentText("Movie details were not updated because no new data was provided.");
//                alert.show();
//            }
//
//            if(!midErrorOccurred) {
//                dialog.setResult(ButtonType.OK);
//                dialog.close();
//            }
        }
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

        previousPriceInput = String.valueOf(currentMovie.getPrice());

        movieCardImage.setImage(Utility.loadImage(currentMovie.getmovieId(), "moviePosters"));
    }
}
