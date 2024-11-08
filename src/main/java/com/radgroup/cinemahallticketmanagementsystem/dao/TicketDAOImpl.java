package com.radgroup.cinemahallticketmanagementsystem.dao;

import com.radgroup.cinemahallticketmanagementsystem.models.Ticket;
import com.radgroup.cinemahallticketmanagementsystem.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAOImpl implements TicketDAO{
    @Override
    public ArrayList<Ticket> getAllTicketsforShowTime(int showTimeID) {
        try{
            Connection con = Database.getConnection();
            String query = "SELECT * FROM tickets WHERE showTimeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, showTimeID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ticket> tickets = new ArrayList<>();
            while(rs.next()){
                tickets.add(new Ticket(
                        rs.getInt("ticketID"),
                        rs.getInt("showTimeId"),
                        rs.getInt("customerID"),
                        rs.getString("empUserName"),
                        rs.getString("seatNo")
                ));
            }
            rs.close();
            ps.close();
            con.close();
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket getTicket(int ticketID) {
        try{
            Connection con = Database.getConnection();
            String query = "SELECT * FROM tickets WHERE ticketID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticketID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Ticket ticket = new Ticket(
                        rs.getInt("ticketID"),
                        rs.getInt("showTimeId"),
                        rs.getInt("customerID"),
                        rs.getString("empUserName"),
                        rs.getString("seatNo")
                    );
                rs.close();
                ps.close();
                con.close();
                return ticket;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        try {
            Connection con = Database.getConnection();
            String query = "INSER INTO tickets (showTimeId, customerID, empUserName, seatNo) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticket.getShowTimeId());
            ps.setInt(2, ticket.getCustomerId());
            ps.setInt(3, ticket.getCustomerId());
            ps.setString(4, ticket.getEmpUsername());
            ps.setString(5, ticket.getSeatNo());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        try{
            Connection con = Database.getConnection();
            String query = "UPDATE tickets SET showTimeId = ?, customerID = ?, empUserName = ?, seatNo = ? WHERE ticketID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticket.getShowTimeId());
            ps.setInt(2, ticket.getCustomerId());
            ps.setString(3, ticket.getEmpUsername());
            ps.setString(4, ticket.getSeatNo());
            ps.setInt(5, ticket.getTicketId());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTicket(int ticketID) {
        try{
            Connection con = Database.getConnection();
            String query = "DELETE FROM tickets WHERE ticketID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticketID);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}