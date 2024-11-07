package com.radgroup.cinemahallticketmanagementsystem.models;

public class Ticket {
    private int ticketId;
    private int showTimeId;
    private int customerId;
    private String empUsername;
    private String seatNo;

    public Ticket(int ticketIdIn, int showTimeIdIn, int customerIdIn, String empUsernameIn, String seatNoIn){
        this.ticketId = ticketIdIn;
        this.showTimeId = showTimeIdIn;
        this.customerId = customerIdIn;
        this.empUsername = empUsernameIn;
        this.seatNo = seatNoIn;
    }

    public int getTicketId(){
        return ticketId;
    }

    public int getShowTimeId(){
        return showTimeId;
    }

    public int setShowTimeId(int newShowTimeId){
        this.showTimeId = newShowTimeId;
    }

    public int getCustomerId(){
        return customerId;
    }

    public int setCustomerId(int newCustomerId){
        this.customerId = newCustomerId;
    }

    public String getEmpUsername(){
        return empUsername;
    }

    public String setEmpUsername(String newEmpUsername){
        this.empUsername = newEmpUsername;
    }

    public String getSeatNo(){
        return seatNo;
    }

    public String setSeatNo(String newSeatNo){
        this.seatNo = newSeatNo;
    }
}
