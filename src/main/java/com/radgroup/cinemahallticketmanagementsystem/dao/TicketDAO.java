package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Ticket;

import java.util.ArrayList;

public interface TicketDAO {
    public ArrayList<Ticket> getAllTicketsforShowTime(int showTimeID);
    public Ticket getTicket(int ticketID);
    public boolean addTicket(Ticket ticket);
    public boolean updateTicket(Ticket ticket);
    public boolean deleteTicket(int ticketID);
}
