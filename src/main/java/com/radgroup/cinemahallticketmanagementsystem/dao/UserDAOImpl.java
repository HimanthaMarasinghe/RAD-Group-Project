package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.User;
import com.radgroup.cinemahallticketmanagementsystem.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(User user) {
        try {
            Connection conn = Database.getConnection();
            String query = "INSERT INTO users (username, name, address, phone) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(String username) {
        try {
            Connection conn = Database.getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(username, name, address, phone, password, role);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateUserDetails(String currentUsername, User user) {
        try{
            Connection conn = Database.getConnection();
            String query = "UPDATE users SET username = ?, name = ?, address = ?, phone = ? WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, currentUsername);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUserPassword(User user) {
        System.out.println("updateUserPassword");
        try{
            Connection conn = Database.getConnection();
            String query = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUsername());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(String username) {
        try{
            Connection conn = Database.getConnection();
            String query = "DELETE FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ArrayList<User> listAllEmployees() {
        ArrayList<User> users = new ArrayList<>();
        try{
            Connection conn = Database.getConnection();
            String query = "SELECT * FROM users WHERE role = 'Employee'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }
}
