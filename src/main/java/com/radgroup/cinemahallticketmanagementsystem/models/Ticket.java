package com.radgroup.cinemahallticketmanagementsystem.models;

import java.util.Objects;

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

    /**
     * When inserting, this constructor should be used. Because ticketId should not be given. It is auto incremented by the DB.
     * @param showTimeIdIn
     * @param customerPhoneIn
     * @param empUsernameIn
     * @param seatNoIn
     */
    public Ticket(int showTimeIdIn, String customerPhoneIn, String empUsernameIn, String seatNoIn){
        this.showTimeId = showTimeIdIn;
        this.customerPhone = customerPhoneIn;
        this.empUsername = empUsernameIn;
        this.seatNo = seatNoIn;
    }

//    public Ticket(int ticketId, int showTimeId, String empUsername, String seatNo) {
//        this.ticketId = ticketId;
//        this.showTimeId = showTimeId;
//        this.empUsername = empUsername;
//        this.seatNo = seatNo;
//    }

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

    public boolean areAttributesEqual(Ticket other) {
        if (other == null) return false;

        return Objects.equals(this.customerPhone, other.customerPhone) &&
                Objects.equals(this.seatNo, other.seatNo) &&
                this.showTimeId == other.showTimeId;
    }
}
