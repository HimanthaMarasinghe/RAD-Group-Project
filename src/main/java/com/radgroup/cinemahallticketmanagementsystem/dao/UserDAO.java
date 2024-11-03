package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.User;

public interface UserDAO {
    public boolean addUser(User user);
    public User getUser(String username);
    public boolean updateUser(User user);
    public boolean deleteUser(String username);
}
