package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.models.Upcoming;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ShowTimeDAO {
    public boolean addShowTime(ShowTime showtime);
    public boolean deleteShowTime(int showid);
    public ShowTime getShowTime(int showid);  //to edit it if we tend to edit it in seperate window
    public boolean updateShowTime(ShowTime showtime);
    public ArrayList<ShowTime> listAllShowTimesForMovie(String movieId, int flag);   //list all
    public int getNumberOfShowTimes(LocalDate fromDate, LocalDate toDate, String timeSlot);
    public ArrayList<String> getAllShowTimes(String movieId, LocalDate date);
    public int getShowId(ShowTime showTime);
    public void updateSeatCount(int flag, int sid);
    public ArrayList<Upcoming> listAllUpcomingShowTimes();
}
