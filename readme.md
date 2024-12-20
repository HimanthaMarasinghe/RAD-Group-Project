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

<style>
    .Contributors{
        display : flex;
        flex-wrap : auto;
        margin-bottom : 25px;
    }

    .profile {
        display : flex;
        flex-direction : column;
        align-items : center;
        height : 100px;
        aspect-ratio : 1;
        text-align : center;
        transition: transform 0.3s ease;
        text-decoration : none;
        color : unset;
    }

    .profile:hover{
        text-decoration : none;
        color : unset;
    }

    .profile img {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        object-fit: cover;
        border: 3px solid #fff;
        transition: transform 0.3s ease; 
    }

    .profile img:hover {
        transform: scale(1.2);
    }
</style>

<div class="Contributors">
    <a href="https://github.com/HimanthaMarasinghe" class="profile">
        <img src="https://github.com/HimanthaMarasinghe.png"/>
        Himantha Marasinghe
    </a>
    <a href="https://github.com/SeniruR" class="profile">
        <img src="https://github.com/SeniruR.png"/>
        Seniru Ranasinghe
    </a>
    <a href="https://github.com/RangikaaHerath001" class="profile">
        <img src="https://github.com/RangikaaHerath001.png"/>
        Rangika Herath
    </a>
    <a href="https://github.com/Nojiyu" class="profile">
        <img src="https://github.com/Nojiyu.png"/>
        Noji Yudara
    </a>
    <a href="https://github.com/anjananadee23" class="profile">
        <img src="https://github.com/anjananadee23.png"/>
        Anjana Nadeeshan
    </a>
</div>

##Here are some screenshots of the interface

![alt text](<Screen Shots/Login.png>)
![alt text](<Screen Shots/Home.png>)
![alt text](<Screen Shots/Book_Ticket.png>)
![alt text](<Screen Shots/ShowTime_Details.png>)
![alt text](<Screen Shots/Ticket_Details.png>)
![alt text](<Screen Shots/Movies.png>)
![alt text](<Screen Shots/Movie_Details.png>)
![alt text](<Screen Shots/Update_movie.png>)
![alt text](<Screen Shots/Customers.png>)
![alt text](<Screen Shots/Employees.png>)
![alt text](<Screen Shots/Update_Employee.png>)
![alt text](<Screen Shots/My_Profile.png>)
