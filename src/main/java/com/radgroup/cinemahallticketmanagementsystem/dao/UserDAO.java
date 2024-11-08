package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.User;

import java.util.ArrayList;

public interface UserDAO {
    public boolean addUser(User user);
    public User getUser(String username);
    public void updateUserDetails(String currentUsername, User user);


    public boolean updateUserPassword(User user);
    public boolean deleteUser(String username);
    public ArrayList<User> listAllEmployees();
}

