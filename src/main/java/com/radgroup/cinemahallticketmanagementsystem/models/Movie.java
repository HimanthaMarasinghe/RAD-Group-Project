package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;
import java.util.Objects;

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

    public boolean areAttributesEqual(Movie other) {
        if (other == null) return false;

        return Objects.equals(this.movieId, other.movieId) &&
                Objects.equals(this.movieName, other.movieName) &&
                Objects.equals(this.duration, other.duration) &&
                this.price == other.price;
    }

}

