package com.icbt.dto;

public class billDto {
    private String reservationNo;
    private String guestName;
    private String roomType;
    private String checkIn;
    private String checkOut;
    private long nights;
    private double ratePerNight;
    private double total;

    public String getReservationNo() { return reservationNo; }
    public void setReservationNo(String reservationNo) { this.reservationNo = reservationNo; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }

    public long getNights() { return nights; }
    public void setNights(long nights) { this.nights = nights; }

    public double getRatePerNight() { return ratePerNight; }
    public void setRatePerNight(double ratePerNight) { this.ratePerNight = ratePerNight; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}