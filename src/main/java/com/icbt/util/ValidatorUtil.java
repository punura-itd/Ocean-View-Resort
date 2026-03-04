package com.icbt.util;

import java.time.LocalDate;

public class ValidatorUtil {

    private ValidatorUtil() {}

    public static boolean isNonBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;

        // remove spaces, hyphens, brackets
        String p = phone.trim().replaceAll("[\\s\\-()]", "");

        // Accept: 07XXXXXXXX, +94 7XXXXXXXX, +947XXXXXXXX, 947XXXXXXXX
        return p.matches("^(\\+94|94|0)?7\\d{8}$");
    }

    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        return checkIn != null && checkOut != null && checkOut.isAfter(checkIn);
    }
}
