package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    public void addCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(String customerId);
    public Customer getCustomer(String customerId);
    public ArrayList<Customer> getAllCustomers();
}
