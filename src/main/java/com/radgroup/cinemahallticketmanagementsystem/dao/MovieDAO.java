package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Movie;

import java.util.ArrayList;

public interface MovieDAO {
    public void add(Movie movie);
    public void update(Movie movie);
    public void delete(String movieId);
    public Movie getMovie(String movieId);
    public ArrayList<Movie> getAllMovies();
}
