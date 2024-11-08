package com.radgroup.cinemahallticketmanagementsystem.models;

public class Ticket {
    private int ticketId;
    private int showTimeId;
    private String customerPhone;
    private String empUsername;
    private String seatNo;

    public Ticket(int ticketIdIn, int showTimeIdIn, String customerPhoneIn, String empUsernameIn, String seatNoIn){
        this(showTimeIdIn, customerPhoneIn, empUsernameIn, seatNoIn);
        this.ticketId = ticketIdIn;
    }

    public Ticket(int showTimeIdIn, String customerPhoneIn, String empUsernameIn, String seatNoIn){
        this.showTimeId = showTimeIdIn;
        this.customerPhone = customerPhoneIn;
        this.empUsername = empUsernameIn;
        this.seatNo = seatNoIn;
    }

    public int getTicketId(){
        return ticketId;
    }

    public int getShowTimeId(){
        return showTimeId;
    }

    public void setShowTimeId(int newShowTimeId){
        this.showTimeId = newShowTimeId;
    }

    public String getCustomerPhone(){
        return customerPhone;
    }

    public void setCustomerPhone(String newCustomerPhone){
        this.customerPhone = newCustomerPhone;
    }

    public String getEmpUsername(){
        return empUsername;
    }

    public void setEmpUsername(String newEmpUsername){
        this.empUsername = newEmpUsername;
    }

    public String getSeatNo(){
        return seatNo;
    }

    public void setSeatNo(String newSeatNo){
        this.seatNo = newSeatNo;
    }
}
