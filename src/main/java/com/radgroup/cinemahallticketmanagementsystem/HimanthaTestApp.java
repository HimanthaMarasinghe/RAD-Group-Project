package com.radgroup.cinemahallticketmanagementsystem;

import com.radgroup.cinemahallticketmanagementsystem.dao.TicketDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.TicketDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAO;
import com.radgroup.cinemahallticketmanagementsystem.dao.UserDAOImpl;
import com.radgroup.cinemahallticketmanagementsystem.models.Ticket;
import com.radgroup.cinemahallticketmanagementsystem.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HimanthaTestApp
{
    public static void main (String[] args){
        TicketDAO ticketDAO = new TicketDAOImpl();
        ticketDAO.addTicket(new Ticket(28, "1234567890", "a", "A1"));
        Ticket ticket = ticketDAO.getTicket(1);
        System.out.println(ticket.getTicketId());
        System.out.println(ticket.getCustomerPhone());
        System.out.println(ticket.getShowTimeId());
        System.out.println(ticket.getEmpUsername());
        System.out.println(ticket.getSeatNo());
    }
}