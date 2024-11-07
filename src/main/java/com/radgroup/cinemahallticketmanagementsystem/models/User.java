package com.radgroup.cinemahallticketmanagementsystem.models;

public class User {
    private String username;
    private String name;
    private String address;
    private String phone;
    private String password;
    private String role;

    public User(String username, String name, String address, String phone, String password, String role) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public User(String username, String name, String address, String phone) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
