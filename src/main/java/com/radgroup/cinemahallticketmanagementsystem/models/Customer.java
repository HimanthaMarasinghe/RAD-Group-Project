
package com.radgroup.cinemahallticketmanagementsystem.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Customer {
    private String name;
    private String phone;
    private LocalDate dateOfBirth;

    public Customer(String name, String phone, LocalDate dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
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

    public boolean areAttributesEqual(Customer other) {
        if (other == null) return false;

        return Objects.equals(this.name, other.name) &&
                Objects.equals(this.phone, other.phone) &&
                Objects.equals(this.dateOfBirth, other.dateOfBirth);
    }
}
