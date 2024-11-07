package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;

public class ShowTime {
    private String showid;
    private LocalDate date;
    private String timeslot;
    private String movieid;

    public ShowTime(String showid, LocalDate date, String timeslot, String movieid) {
        this.showid = showid;
        this.date = date;
        this.timeslot = timeslot;
        this.movieid = movieid;
    }

    public String getShowid() {
        return showid;
    }

    public void setShowid(String showid) {
        this.showid = showid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }
}