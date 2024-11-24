package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;

public class Upcoming extends ShowTime{
//    private LocalDate date;
//    private String time;
    private String movie;

    public Upcoming(LocalDate date, String timeslot, String movie, int seats, String movieid, int showid) {
        super(showid, date, timeslot, movieid, seats);
        this.movie = movie;
    }

    public String getMovie() {
        return movie;
    }
//    private String seat;
//    private String movieId;
//
//    public Upcoming(LocalDate date, String time, String movie, String seat, String movieId) {
//        this.date = date;
//        this.time = time;
//        this.movie = movie;
//        this.seat = seat;
//        this.movieId = movieId;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public String getMovie() {
//        return movie;
//    }
//
//    public String getMovieId() {
//        return movieId;
//    }
//
//    public String getSeat() {
//        return seat;
//    }
}
