package com.icbt.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BillDtoTest {

    @Test
    void settersAndGetters_shouldWorkForAllFields() {
        billDto dto = new billDto();

        assertNull(dto.getReservationNo());
        assertNull(dto.getGuestName());
        assertNull(dto.getRoomType());
        assertNull(dto.getCheckIn());
        assertNull(dto.getCheckOut());
        assertEquals(0L, dto.getNights());
        assertEquals(0.0, dto.getRatePerNight());
        assertEquals(0.0, dto.getTotal());

        dto.setReservationNo("R-101");
        dto.setGuestName("Bimal");
        dto.setRoomType("DELUXE");
        dto.setCheckIn("2026-06-01");
        dto.setCheckOut("2026-06-04");
        dto.setNights(3);
        dto.setRatePerNight(25000.0);
        dto.setTotal(75000.0);

        assertEquals("R-101", dto.getReservationNo());
        assertEquals("Bimal", dto.getGuestName());
        assertEquals("DELUXE", dto.getRoomType());
        assertEquals("2026-06-01", dto.getCheckIn());
        assertEquals("2026-06-04", dto.getCheckOut());
        assertEquals(3L, dto.getNights());
        assertEquals(25000.0, dto.getRatePerNight());
        assertEquals(75000.0, dto.getTotal());
    }
}
