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
            String query = "INSERT INTO users VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
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
            Connection con = Database.getConnection();
            String query = "UPDATE users SET username = ?, name = ?, address = ?, phone = ? WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, currentUsername);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public boolean updateUserPassword(User user) {
        // This method is used to update ONLY THE PASSWORD.
        // username should be used in WHERE clause to filter data.
        //Ex : "UPDATE users SET password = ? WHERE username = ?"

        // Other fields (username, name, phone, address, role) in the database should not be changed
        // The user object that is passed to this method only have the new password and the username.
        // Therefore, user.getName(), user.getPhone(), user.getAddress(), user.getRole() method are NOT ALLOWED HERE.
        // ONLY user.getUsername() nad user.getPassword() method can be used.
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }
}
