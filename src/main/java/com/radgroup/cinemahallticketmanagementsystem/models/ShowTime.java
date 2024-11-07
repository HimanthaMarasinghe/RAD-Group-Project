package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;
import java.util.Objects;

public class ShowTime {
    private int showid;
    private LocalDate date;
    private String timeslot;
    private String movieid;

    /**
     * Number of seats available for this Show Time.
     */
    private int seats;

    /**
     * When Updating show times in DB, this constructor should be used.
     * Because seats (Available seats) can not be updated when showTime update.
     * seats (Available seats) only get changed when adding or deleting tickets.
     * @param showid
     * @param date
     * @param timeslot
     * @param movieid
     */
    public ShowTime(int showid, LocalDate date, String timeslot, String movieid) {
        this(date, timeslot, movieid);
        this.showid = showid;
    }

    /**
     * When adding new show times to the DB this constructor should be used.
     * Because,
     *    - showId is auto incremented by DB
     *    - all the seats are available for new show times. DB will use the default value.
     * @param date
     * @param timeslot
     * @param movieid
     */
    public ShowTime(LocalDate date, String timeslot, String movieid){
        this.date = date;
        this.timeslot = timeslot;
        this.movieid = movieid;
    }

    public ShowTime(int showid, LocalDate date, String timeslot, String movieid, int seats) {
        this(showid, date, timeslot, movieid);
        this.seats = seats;
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean areAttributesEqual(ShowTime other) {
        if (other == null) return false;

        return  Objects.equals(this.date, other.date) &&
                Objects.equals(this.timeslot, other.timeslot) &&
                Objects.equals(this.movieid, other.movieid);
    }
}