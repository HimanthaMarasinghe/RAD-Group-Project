package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;

public class Movie {

    private String movieId;
    private String movieName;
    private String duration;
    private int price;


    public Movie(String movieId, String movieName, String duration, int price) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
        this.price = price;
    }

    // Getters and setters
    public String getmovieId() {
        return movieId;
    }

    public void setmovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getmovieName() {
        return movieName;
    }

    public void setmovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

