package com.icbt.service;

import com.icbt.dao.reservationDao;
import com.icbt.dao.roomRateDao;
import com.icbt.dto.billDto;
import com.icbt.model.reservation;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class billingService {

    private final reservationDao reservationDao;
    private final roomRateDao roomRateDao;

    public billingService(reservationDao reservationDao, roomRateDao roomRateDao) {
        this.reservationDao = reservationDao;
        this.roomRateDao = roomRateDao;
    }

    public Optional<billDto> generateBill(String reservationNo) {
        if (reservationNo == null || reservationNo.trim().isEmpty()) return Optional.empty();

        Optional<reservation> optRes = reservationDao.findByReservationNo(reservationNo.trim());
        if (optRes.isEmpty()) return Optional.empty();

        reservation r = optRes.get();

        String roomType = r.getRoomType() == null ? "" : r.getRoomType().trim().toUpperCase();

        Optional<Double> rateOpt = roomRateDao.findRateByRoomType(roomType);
        if (rateOpt.isEmpty()) return Optional.empty();

        long nights = ChronoUnit.DAYS.between(r.getCheckIn(), r.getCheckOut());
        if (nights <= 0) return Optional.empty();

        double rate = rateOpt.get();
        double total = nights * rate;

        billDto dto = new billDto();
        dto.setReservationNo(r.getReservationNo());
        dto.setGuestName(r.getGuestName());
        dto.setRoomType(roomType);
        dto.setCheckIn(String.valueOf(r.getCheckIn()));
        dto.setCheckOut(String.valueOf(r.getCheckOut()));
        dto.setNights(nights);
        dto.setRatePerNight(rate);
        dto.setTotal(total);

        return Optional.of(dto);
    }
}