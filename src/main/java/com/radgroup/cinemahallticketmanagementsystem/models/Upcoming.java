package com.radgroup.cinemahallticketmanagementsystem.models;

public class Upcoming {
    private String date;
    private String time;
    private String movie;
    private String seat;

    public Upcoming(String date, String time, String movie, String seat) {
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMovie() {
        return movie;
    }

    public String getSeat() {
        return seat;
    }
}
