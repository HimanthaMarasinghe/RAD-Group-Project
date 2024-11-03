package com.radgroup.cinemahallticketmanagementsystem;

import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.User;

import java.util.Scanner;

public class HimanthaTestApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add username");
        String username = sc.next();
//        System.out.println("Add password");
//        String password = sc.next();
//        System.out.println("Add role");
//        String role = sc.next();
//        User user = new User(username, password, role);
//        UserDAO U = new UserDAOImpl();
//        U.addUser(user);

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Role: " + user.getRole());

    }
}