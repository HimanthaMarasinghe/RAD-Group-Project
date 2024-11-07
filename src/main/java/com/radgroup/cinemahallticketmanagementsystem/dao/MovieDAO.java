package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Movie;

import java.util.ArrayList;

public interface MovieDAO {
    public boolean addMovie(Movie Movie);
    public boolean deleteMovie(String movieId);
    public Movie getMovie(String movieId);  //to edit it if we tend to edit it in seperate window
    public boolean updateMovie(Movie Movie, String previousId);
    public ArrayList<Movie> listAllMovies();   //list all
}
