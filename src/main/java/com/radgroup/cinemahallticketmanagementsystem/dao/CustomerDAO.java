package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    public void addCustomer(Customer customer);
    public void updateCustomer(String currentCustomerPhone, Customer customer);
    public void deleteCustomer(String customerPhone);
    public Customer getCustomer(String customerPhone);
    public ArrayList<Customer> getAllCustomers();
}
