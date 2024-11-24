package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
import com.radgroup.cinemahallticketmanagementsystem.models.Upcoming;
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

    public ArrayList<ShowTime> listAllShowTimesForMovie(String movieId, int flag) {
        ArrayList<ShowTime> showtimes = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM showtime WHERE mid = ?";
            if (flag == 1)
                sql += " AND date > CURDATE()";
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
    public ArrayList<String> getAllShowTimes(String movieId, LocalDate date) {
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT timeslot FROM showtime WHERE mid = ? AND date = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, movieId);
            statement.setDate(2, java.sql.Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> showtimes = new ArrayList<>();
            while (resultSet.next()) {
                showtimes.add(resultSet.getString("timeslot"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return showtimes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getShowId(ShowTime showTime) {
        try {
            Connection connection = Database.getConnection();
            String sql = "SELECT sid FROM showtime WHERE date = ? AND timeslot = ? AND mid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(showTime.getDate()));
            ps.setString(2, showTime.getTimeslot());
            ps.setString(3, showTime.getMovieid());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("sid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void updateSeatCount(int flag, int sid) {
        try {
            Connection con = Database.getConnection();
            String query;
            if (flag == 1)
                query = "UPDATE showtime SET availableSeats = availableSeats - 1 WHERE sid = ?";
            else
                query = "UPDATE showtime SET availableSeats = availableSeats + 1 WHERE sid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, sid);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Upcoming> listAllUpcomingShowTimes() {
        try {
            Connection con = Database.getConnection();
            String sql = "SELECT date, timeslot, movie.MName, availableSeats, showtime.mid, sid FROM showtime INNER JOIN movie ON showtime.mid = movie.mid WHERE date > CURDATE() ORDER BY date ASC, timeslot ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Upcoming> upcomings = new ArrayList<>();
            while (resultSet.next()) {
                Upcoming upcoming = new Upcoming(
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("timeslot"),
                        resultSet.getString("movie.MName"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("mid"),
                        resultSet.getInt("sid")
                );
                upcomings.add(upcoming);
            }
            resultSet.close();
            ps.close();
            con.close();
            return upcomings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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