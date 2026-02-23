package com.icbt.util;

import java.time.LocalDate;

public class ValidatorUtil {

    private ValidatorUtil() {}

    public static boolean isNonBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        // Sri Lanka mobile often 10 digits; allow +94 optional
        String p = phone.trim();
        return p.matches("^(94|0)?7{8}$");
    }

    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        return checkIn != null && checkOut != null && checkOut.isAfter(checkIn);
    }
}
