
package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;
import java.util.Date;

public class Customer {
    private int customerId;
    private String name;
    private String phone;
    private LocalDate dateOfBirth;

    public Customer(int customerId, String name, String phone, LocalDate dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
