package com.icbt.dto;

public class dashboardStatsDto {
    private int totalReservations;
    private int todayCheckIns;
    private int todayCheckOuts;
    private double totalRevenue;   // from bills table
    private int totalBills;

    public int getTotalReservations() { return totalReservations; }
    public void setTotalReservations(int totalReservations) { this.totalReservations = totalReservations; }

    public int getTodayCheckIns() { return todayCheckIns; }
    public void setTodayCheckIns(int todayCheckIns) { this.todayCheckIns = todayCheckIns; }

    public int getTodayCheckOuts() { return todayCheckOuts; }
    public void setTodayCheckOuts(int todayCheckOuts) { this.todayCheckOuts = todayCheckOuts; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

    public int getTotalBills() { return totalBills; }
    public void setTotalBills(int totalBills) { this.totalBills = totalBills; }
}