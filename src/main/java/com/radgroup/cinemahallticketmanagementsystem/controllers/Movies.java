package com.radgroup.cinemahallticketmanagementsystem.controllers;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Movies extends CoreController implements Cont {

    @FXML
    private ScrollPane movieScrloPane;

    @FXML
    void handleAddNewMovie(ActionEvent event) {
        showDialogBox("NewMovie", "Add New Movie");
        movieListRefresh();
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        movieListRefresh();
    }

    @Override
    public void setView(Object data) {
        movieListRefresh();
    }
    
    public void movieListRefresh(){
        System.out.println("Movies");
        MovieDAO movieDAO = new MovieDAOImpl();
        ArrayList<Movie> movies = movieDAO.listAllMovies();
        GridPane movieGrid = new GridPane();

        int col = 0;
        int row = 0;

        try {
            for (Movie movie : movies) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/UIcomponents/MovieCard.fxml"));
                VBox movieCardVBox = fxmlLoader.load();
                MovieCard movieCardController = fxmlLoader.getController();

                Object[] objectArray = {movie, this};
                movieCardController.setData(objectArray);
                movieGrid.add(movieCardVBox, col++, row);
                if (col == 3){
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        movieGrid.setVgap(10);
        movieGrid.setHgap(10);
        movieScrloPane.setContent(movieGrid);
    }
    
    

}
