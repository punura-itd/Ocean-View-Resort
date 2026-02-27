package com.icbt.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DashboardStatsDtoTest {

    @Test
    void settersAndGetters_shouldWorkForAllFields() {
        dashboardStatsDto dto = new dashboardStatsDto();

        assertEquals(0, dto.getTotalReservations());
        assertEquals(0, dto.getTodayCheckIns());
        assertEquals(0, dto.getTodayCheckOuts());
        assertEquals(0.0, dto.getTotalRevenue());
        assertEquals(0, dto.getTotalBills());

        dto.setTotalReservations(150);
        dto.setTodayCheckIns(7);
        dto.setTodayCheckOuts(5);
        dto.setTotalRevenue(1250000.50);
        dto.setTotalBills(140);

        assertEquals(150, dto.getTotalReservations());
        assertEquals(7, dto.getTodayCheckIns());
        assertEquals(5, dto.getTodayCheckOuts());
        assertEquals(1250000.50, dto.getTotalRevenue());
        assertEquals(140, dto.getTotalBills());
    }
}
