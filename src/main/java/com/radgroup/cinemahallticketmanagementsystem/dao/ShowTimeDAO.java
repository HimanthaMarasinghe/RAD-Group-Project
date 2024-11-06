package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;

import java.util.ArrayList;

public interface ShowTimeDAO {
    public boolean addShowTime(ShowTime showtime);
    public boolean deleteShowTime(String showid);
    public ShowTime getShowTime(String showid);  //to edit it if we tend to edit it in seperate window
    public boolean updateShowTime(ShowTime showtime);
    public ArrayList<ShowTime> listAllShowTimesForMovie(String movieId);   //list all
}
