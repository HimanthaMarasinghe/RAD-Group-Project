package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Customer;
import com.radgroup.cinemahallticketmanagementsystem.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = Database.getConnection()) {
            String sql = "INSERT INTO customer (customerId, name, phone, dateOfBirth) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getPhone());
            statement.setDate(4, java.sql.Date.valueOf(customer.getDateOfBirth())); // Convert LocalDate to java.sql.Date
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE customer SET name = ?, phone = ?, dateOfBirth = ? WHERE customerId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.setDate(3, java.sql.Date.valueOf(customer.getDateOfBirth())); // Convert LocalDate to java.sql.Date
            statement.setString(4, customer.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        try (Connection connection = Database.getConnection()) {
            String sql = "DELETE FROM customer WHERE customerId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getCustomer(String customerId) {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM customer WHERE customerId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dateOfBirth").toLocalDate() // Convert java.sql.Date to LocalDate
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM customer";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dateOfBirth").toLocalDate() // Convert java.sql.Date to LocalDate
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
