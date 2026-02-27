package com.icbt.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReservationDtoTest {

    @Test
    void settersAndGetters_shouldWorkForAllFields() {
        reservationDto dto = new reservationDto();

        assertNull(dto.getReservationNo());
        assertNull(dto.getGuestName());
        assertNull(dto.getAddress());
        assertNull(dto.getContact());
        assertNull(dto.getRoomType());
        assertNull(dto.getCheckIn());
        assertNull(dto.getCheckOut());

        dto.setReservationNo("R-100");
        dto.setGuestName("Asha");
        dto.setAddress("Colombo");
        dto.setContact("0700000000");
        dto.setRoomType("SUITE");
        dto.setCheckIn("2026-05-01");
        dto.setCheckOut("2026-05-03");

        assertEquals("R-100", dto.getReservationNo());
        assertEquals("Asha", dto.getGuestName());
        assertEquals("Colombo", dto.getAddress());
        assertEquals("0700000000", dto.getContact());
        assertEquals("SUITE", dto.getRoomType());
        assertEquals("2026-05-01", dto.getCheckIn());
        assertEquals("2026-05-03", dto.getCheckOut());
    }
}
