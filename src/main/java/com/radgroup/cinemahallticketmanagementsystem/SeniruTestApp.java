//package com.radgroup.cinemahallticketmanagementsystem;
//
//import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAO;
//import com.radgroup.cinemahallticketmanagementsystem.dao.ShowTimeDAOImpl;
//import com.radgroup.cinemahallticketmanagementsystem.models.ShowTime;
//
//import java.time.LocalDate;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class SeniruTestApp {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ShowTimeDAO showTimeDAO = new ShowTimeDAOImpl();
//
//        int choice;
//
//        do {
//            System.out.println("\nShowTime Management Menu");
//            System.out.println("1. Add ShowTime");
//            System.out.println("2. Delete ShowTime");
//            System.out.println("3. Get ShowTime Details");
//            System.out.println("4. List ShowTimes for Movie (by ID)");
//            System.out.println("5. Update ShowTime");
//            System.out.println("6. Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline character
//
//            switch (choice) {
//                case 1:
//                    addShowTime(showTimeDAO, scanner);
//                    break;
//                case 2:
//                    deleteShowTime(showTimeDAO, scanner);
//                    break;
//                case 3:
//                    getShowTimeDetails(showTimeDAO, scanner);
//                    break;
//                case 4:
//                    listShowTimesForMovie(showTimeDAO, scanner);
//                    break;
//                case 5:
//                    updateShowTime(showTimeDAO, scanner);
//                    break;
//                case 6:
//                    System.out.println("Exiting ShowTime Management Menu.");
//                    break;
//                default:
//                    System.out.println("Invalid Choice!");
//            }
//        } while (choice != 6);
//    }
//
//    private static void addShowTime(ShowTimeDAO showTimeDAO, Scanner scanner) {
//        System.out.print("Enter Show ID: ");
//        String showId = scanner.nextLine();
//        System.out.print("Enter Date (YYYY-MM-DD): ");
//        String dateString = scanner.nextLine();
//        System.out.print("Enter Timeslot: ");
//        String timeSlot = scanner.nextLine();
//        System.out.print("Enter Movie ID: ");
//        String movieId = scanner.nextLine();
//
//        LocalDate localDate = LocalDate.parse(dateString);
//        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//        ShowTime showTime = new ShowTime(showId, sqlDate.toLocalDate(), timeSlot, movieId);
//
//        if (showTimeDAO.addShowTime(showTime)) {
//            System.out.println("ShowTime added successfully!");
//        } else {
//            System.out.println("Failed to add ShowTime!");
//        }
//    }
//
//    private static void deleteShowTime(ShowTimeDAO showTimeDAO, Scanner scanner) {
//        System.out.print("Enter Show ID to delete: ");
//        String showId = scanner.nextLine();
//
//        if (showTimeDAO.deleteShowTime(showId)) {
//            System.out.println("ShowTime deleted successfully!");
//        } else {
//            System.out.println("Failed to delete ShowTime!");
//        }
//    }
//
//    private static void getShowTimeDetails(ShowTimeDAO showTimeDAO, Scanner scanner) {
//        System.out.print("Enter Show ID: ");
//        String showId = scanner.nextLine();
//
//        ShowTime showTime = showTimeDAO.getShowTime(showId);
//
//        if (showTime != null) {
//            System.out.println(showTime.getShowid()+" "+showTime.getTimeslot());
//        } else {
//            System.out.println("ShowTime not found!");
//        }
//    }
//
//    private static void listShowTimesForMovie(ShowTimeDAO showTimeDAO, Scanner scanner) {
//        System.out.print("Enter Movie ID: ");
//        String movieId = scanner.next();
//        scanner.nextLine(); // Consume newline character
//
//        ArrayList<ShowTime> showTimes = showTimeDAO.listAllShowTimesForMovie(movieId);
//
//        if (showTimes.isEmpty()) {
//            System.out.println("No ShowTimes found for this Movie ID.");
//        } else {
//            System.out.println("ShowTimes for Movie ID " + movieId + ":");
//            for (ShowTime showTime : showTimes) {
//                System.out.println(showTime.getShowid()+" "+showTime.getTimeslot());
//            }
//        }
//    }
//
//    private static void updateShowTime(ShowTimeDAO showTimeDAO, Scanner scanner) {
//        System.out.print("Enter Show ID to update: ");
//        String showId = scanner.nextLine();
//
//        ShowTime existingShowTime = showTimeDAO.getShowTime(showId);
//        if (existingShowTime == null) {
//            System.out.println("ShowTime not found!");
//            return;
//        }
//
//        System.out.print("Enter new Date (YYYY-MM-DD) (Optional, press Enter to keep existing): ");
//        String newDate = scanner.nextLine();
//
//        if (!newDate.isEmpty()) { // Check if user entered a new date
//            LocalDate localDate = LocalDate.parse(newDate);
//            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//            existingShowTime.setDate(sqlDate.toLocalDate());
//        }
//
//        System.out.print("Enter new Timeslot: ");
//        String newTimeSlot = scanner.nextLine();
//
//        existingShowTime.setTimeslot(newTimeSlot); // Update timeslot regardless
//
//        if (showTimeDAO.updateShowTime(existingShowTime)) {
//            System.out.println("ShowTime updated successfully!");
//        } else {
//            System.out.println("Failed to update ShowTime!");
//        }
//    }
//}