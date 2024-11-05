package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowTimeDAOImpl implements ShowTimeDAO {

    @Override
    public boolean addShowTime(ShowTime showtime) {
        try {
            Connection connection = Database.getConnection();
            String sql = "INSERT INTO showtime VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, showtime.getShowid());
            statement.setDate(2, new java.sql.Date(showtime.getDate().getTime()));
            statement.setString(3, showtime.getTimeslot());
            statement.setString(4, showtime.getMovieid());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteShowTime(String showid) {
        try {
            Connection connection = Database.getConnection();
            String sql = "DELETE FROM showtime WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, showid);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShowTime getShowTime(String showid) {
        try {
            Connection connection = Database.getConnection();
            String sql = "SELECT * FROM showtime WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, showid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShowTime showtime = new ShowTime(
                        resultSet.getString("sid"),
                        resultSet.getDate("date"),
                        resultSet.getString("timeslot"),
                        resultSet.getString("mid"));
                return showtime;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<ShowTime> listAllShowTimesForMovie(int movieId) {
        ArrayList<ShowTime> showtimes = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM showtime WHERE mid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ShowTime showtime = new ShowTime(
                        resultSet.getString("sid"),
                        resultSet.getDate("date"),
                        resultSet.getString("timeslot"),
                        resultSet.getString("mid")
                );
                showtimes.add(showtime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return showtimes;
    }

    @Override
    public boolean updateShowTime(ShowTime showtime) {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE showtime SET date = ?, timeslot = ? WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(showtime.getDate().getTime()));
            statement.setString(2, showtime.getTimeslot());
            statement.setString(3, showtime.getShowid());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}