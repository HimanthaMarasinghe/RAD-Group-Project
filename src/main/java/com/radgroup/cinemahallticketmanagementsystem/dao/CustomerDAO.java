package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    public void addCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(String id);
    public Customer getCustomer(String id);
    public ArrayList<Customer> getAllCustomers();
}
