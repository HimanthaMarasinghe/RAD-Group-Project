package com.radgroup.cinemahallticketmanagementsystem;

import com.radgroup.cinemahallticketmanagementsystem.dao.CustomerDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.CustomerDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class RangikaaTestApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAOImpl();

        int choice;

        do {
            System.out.println("\nCustomer Management Menu");
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Get Customer Details");
            System.out.println("4. List All Customers");
            System.out.println("5. Update Customer");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addCustomer(customerDAO, scanner);
                    break;
                case 2:
                    deleteCustomer(customerDAO, scanner);
                    break;
                case 3:
                    getCustomerDetails(customerDAO, scanner);
                    break;
                case 4:
                    listAllCustomers(customerDAO);
                    break;
                case 5:
                    updateCustomer(customerDAO, scanner);
                    break;
                case 6:
                    System.out.println("Exiting Customer Management Menu.");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 6);
    }

    private static void addCustomer(CustomerDAO customerDAO, Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dobString = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = formatter.parse(dobString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            return;
        }

        Customer customer = new Customer(name, phone, dateOfBirth);

        if (customerDAO.addCustomer(customer)) {
            System.out.println("Customer added successfully!");
        } else {
            System.out.println("Failed to add customer!");
        }
    }

    private static void deleteCustomer(CustomerDAO customerDAO, Scanner scanner) {
        System.out.print("Enter Customer ID to delete: ");
        String customerId = scanner.nextLine();

        if (customerDAO.deleteCustomer(customerId)) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Failed to delete customer!");
        }
    }

    private static void getCustomerDetails(CustomerDAO customerDAO, Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = customerDAO.getCustomer(customerId);

        if (customer != null) {
            System.out.println("Customer Details:");
            System.out.println("Name: " + customer.getName());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Date of Birth: " + customer.getDateOfBirth());
        } else {
            System.out.println("Customer not found!");
        }
    }

    private static void listAllCustomers(CustomerDAO customerDAO) {
        ArrayList<Customer> customers = customerDAO.listAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("Customer List:");
            for (Customer customer : customers) {
                System.out.println("Name: " + customer.getName() + ", Phone: " + customer.getPhone() +
                        ", Date of Birth: " + customer.getDateOfBirth());
            }
        }
    }

    private static void updateCustomer(CustomerDAO customerDAO, Scanner scanner) {
        System.out.print("Enter Customer ID to update: ");
        String customerId = scanner.nextLine();

        Customer existingCustomer = customerDAO.getCustomer(customerId);
        if (existingCustomer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter new Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new Phone: ");
        String newPhone = scanner.nextLine();
        System.out.print("Enter new Date of Birth (YYYY-MM-DD): ");
        String newDob = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = formatter.parse(newDob);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            return;
        }

        existingCustomer.setName(newName);
        existingCustomer.setPhone(newPhone);
        existingCustomer.setDateOfBirth(dateOfBirth);

        if (customerDAO.updateCustomer(existingCustomer)) {
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Failed to update customer!");
        }
    }
}
