package com.icbt.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginDtoTest {

    @Test
    void noArgsConstructor_andSetters_shouldUpdateFields() {
        loginDto dto = new loginDto();

        assertNull(dto.getUsername());
        assertNull(dto.getPassword());

        dto.setUsername("admin");
        dto.setPassword("1234");

        assertEquals("admin", dto.getUsername());
        assertEquals("1234", dto.getPassword());
    }

    @Test
    void allArgsConstructor_shouldSetFields() {
        loginDto dto = new loginDto("manager", "pass@123");

        assertEquals("manager", dto.getUsername());
        assertEquals("pass@123", dto.getPassword());
    }
}
