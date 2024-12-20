# Cinema Hall Ticket Booking System

## Project Overview
This Cinema Hall Ticket Booking System is a Java-based desktop application developed using JavaFX for the user interface, MySQL as the database, and Maven for project management and dependencies. The system is designed to used by cinema hall manager and employees and it simplify the process of booking tickets, managing movie schedules, and maintaining customer records for a cinema hall.

## Features
- **User Authentication**: Users can login as a manager or as an employee.
- **Movie Management**: Add, update, and remove movies and schedules.
- **Seat Selection**: Interactive seat selection.
- **Booking Management**: Book seats, view, update and delete bookings.
- **Customer Records**: Manage customer details and booking history.
- **Real-Time Updates**: Live updates on seat availability.

## Technology Stack
- **Programming Language**: Java
- **Framework**: JavaFX
- **Database**: MySQL
- **Build Tool**: Maven

## Prerequisites
To run this project, ensure the following are installed on your system:
- JDK 8 or higher
- MySQL Server

## Setup Instructions
1. **Clone the Repository**:
   ```
   git clone https://github.com/HimanthaMarasinghe/RAD-Group-Project.git
   ```

2. **Create the database**:

- Export the [DB_Template/cinema.sql](DB_Template/cinema.sql) file in to your MySql server. It will create the databse and tables.

3. **Run the project using Maven Wrapper**:
- Open cmd or any other comand line aplication in the project root folder and run folowing code.
    ```
    ./mvnw javafx:run
    ```

## Usage
- Log in using folowing credentials. \
`UserName = cinema`\
`Password = cinema`
- Explore and enjoy the app.

## Team Members

- [Himantha Marasinghe](https://github.com/HimanthaMarasinghe)
- [Seniru Ranasinghe](https://github.com/SeniruR)
- [Rangika Herath](https://github.com/RangikaaHerath001)
- [Noji Yudara](https://github.com/Nojiyu)
- [Anjana Nadeeshan](https://github.com/anjananadee23)


## Here are some screenshots of the interface

![Login](<Screen Shots/Login.png>)
![Home](<Screen Shots/Home.png>)
![Book_Ticket](<Screen Shots/Book_Ticket.png>)
![ShowTime_Details](<Screen Shots/ShowTime_Details.png>)
![Ticket_Details](<Screen Shots/Ticket_Details.png>)
![Movies](<Screen Shots/Movies.png>)
![Movie_Details](<Screen Shots/Movie_Details.png>)
![Update_Movie](<Screen Shots/Update_movie.png>)
![Customers](<Screen Shots/Customers.png>)
![Employees](<Screen Shots/Employees.png>)
![Update_Employee](<Screen Shots/Update_Employee.png>)
![My_Profile](<Screen Shots/My_Profile.png>)
