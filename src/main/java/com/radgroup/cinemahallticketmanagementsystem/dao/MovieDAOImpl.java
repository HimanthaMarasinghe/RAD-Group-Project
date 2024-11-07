package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Movie;
import com.radgroup.cinemahallticketmanagementsystem.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public boolean addMovie(Movie Movie) {
        try {
            Connection connection = Database.getConnection();
            String sql = "INSERT INTO movie VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Movie.getmovieId());
            statement.setString(2, Movie.getmovieName());
            statement.setString(3, Movie.getDuration());
            statement.setInt(4, Movie.getPrice());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteMovie(String MovieId) {
        try {
            Connection connection = Database.getConnection();
            String sql = "DELETE FROM Movie WHERE MId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, MovieId);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie getMovie(String MovieId) {
        try {
            Connection connection = Database.getConnection();
            String sql = "SELECT * FROM Movie WHERE MId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, MovieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getString("Mid"),
                        resultSet.getString("MName"),
                        resultSet.getString("Duration"),
                        resultSet.getInt("price"));
                return movie;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Movie> listAllMovies() {
        ArrayList<Movie> Movie = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM Movie ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getString("Mid"),
                        resultSet.getString("MName"),
                        resultSet.getString("Duration"),
                        resultSet.getInt("price")
                );
                Movie.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Movie;
    }

    @Override
    public boolean updateMovie(Movie Movie, String previousId) {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE Movie SET MName = ?, duration = ?, price = ?, Mid = ? WHERE Mid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Movie.getmovieName());
            statement.setString(2, Movie.getDuration());
            statement.setInt(3, Movie.getPrice());
            statement.setString(4, Movie.getmovieId());
            statement.setString(5, previousId);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
