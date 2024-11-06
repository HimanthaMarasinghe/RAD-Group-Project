package com.radgroup.cinemahallticketmanagementsystem;

import com.radgroup.cinemahallticketmanagementsystem.dao.MovieDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Movie;

import java.util.Scanner;

public class NojiTestApp {
    public static void main(String[] args) {
        MovieDAOImpl movieDAO = new MovieDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Cinema Hall Ticket Management System ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Delete Movie");
            System.out.println("3. Get Movie Details");
            System.out.println("4. Update Movie");
            System.out.println("5. List All Movies");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Movie ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Duration (HH:MM:SS): ");
                    String duration = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    int price = scanner.nextInt();

                    Movie movieToAdd = new Movie(id, name, duration, price);
                    if (movieDAO.addMovie(movieToAdd)) {
                        System.out.println("Movie added successfully!");
                    } else {
                        System.out.println("Failed to add movie.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Movie ID to delete: ");
                    String deleteId = scanner.nextLine();
                    if (movieDAO.deleteMovie(deleteId)) {
                        System.out.println("Movie deleted successfully!");
                    } else {
                        System.out.println("Failed to delete movie.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Movie ID to retrieve: ");
                    String getId = scanner.nextLine();
                    Movie retrievedMovie = movieDAO.getMovie(getId);
                    if (retrievedMovie != null) {
                        System.out.println("Movie Details:");
                        System.out.println("ID: " + retrievedMovie.getmovieId());
                        System.out.println("Name: " + retrievedMovie.getmovieName());
                        System.out.println("Duration: " + retrievedMovie.getDuration());
                        System.out.println("Price: " + retrievedMovie.getPrice());
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Movie ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter New Movie Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Duration (HH:MM:SS): ");
                    String newDuration = scanner.nextLine();
                    System.out.print("Enter New Price: ");
                    int newPrice = scanner.nextInt();

                    Movie movieToUpdate = new Movie(updateId, newName, newDuration, newPrice);
                    if (movieDAO.updateMovie(movieToUpdate)) {
                        System.out.println("Movie updated successfully!");
                    } else {
                        System.out.println("Failed to update movie.");
                    }
                    break;

                case 5:
                    System.out.println("Listing All Movies:");
                    for (Movie movie : movieDAO.listAllMovies()) {
                        System.out.println("ID: " + movie.getmovieId() + ", Name: " + movie.getmovieName() +
                                ", Duration: " + movie.getDuration() + ", Price: " + movie.getPrice());
                    }
                    break;

                case 6:
                    System.out.println("Exiting");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
