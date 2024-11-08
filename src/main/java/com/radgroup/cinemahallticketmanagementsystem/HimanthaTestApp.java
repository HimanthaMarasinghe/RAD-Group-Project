package com.radgroup.cinemahallticketmanagementsystem;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HimanthaTestApp
{
    public static void main (String[] args){
        UserDAO UDAO = new UserDAOImpl();
        User user = new User("a", "a", "a", "a", "updated", "emp");
        UDAO.updateUserPassword(user);
    }
}