package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ShowTimeDAOImpl implements ShowTimeDAO {

    @Override
    public boolean addShowTime(ShowTime showtime) {
        try {
            Connection connection = Database.getConnection();
            String sql = "INSERT INTO showtime (date, timeslot, mid) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(showtime.getDate()));
            statement.setString(2, showtime.getTimeslot());
            statement.setString(3, showtime.getMovieid());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteShowTime(int showid) {
        try {
            Connection connection = Database.getConnection();
            String sql = "DELETE FROM showtime WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, showid);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShowTime getShowTime(int showid) {
        try {
            Connection connection = Database.getConnection();
            String sql = "SELECT * FROM showtime WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, showid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShowTime showtime = new ShowTime(
                        resultSet.getInt("sid"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("timeslot"),
                        resultSet.getString("mid"),
                        resultSet.getInt("availableSeats"));
                return showtime;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<ShowTime> listAllShowTimesForMovie(String movieId) {
        ArrayList<ShowTime> showtimes = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM showtime WHERE mid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ShowTime showtime = new ShowTime(
                        resultSet.getInt("sid"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("timeslot"),
                        resultSet.getString("mid"),
                        resultSet.getInt("availableSeats"));
                showtimes.add(showtime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return showtimes;
    }

    @Override
    public int getNumberOfShowTimes(LocalDate fromDate, LocalDate toDate, String timeSlot) {
        try {
            Connection connection = Database.getConnection();
            String sql = "SELECT COUNT(*) FROM showtime WHERE timeslot = ? AND date BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, timeSlot);
            statement.setDate(2, java.sql.Date.valueOf(fromDate));
            statement.setDate(3, java.sql.Date.valueOf(toDate));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public boolean updateShowTime(ShowTime showtime) {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE showtime SET date = ?, timeslot = ?, mid = ? WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(showtime.getDate()));
            statement.setString(2, showtime.getTimeslot());
            statement.setString(3, showtime.getMovieid());
            statement.setInt(4, showtime.getShowid());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}