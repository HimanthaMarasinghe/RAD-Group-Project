package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.User;

public interface UserDAO {
    public boolean addUser(User user);
    public User getUser(int id);
    public boolean updateUser(User user);
    public boolean deleteUser(int id);
}
