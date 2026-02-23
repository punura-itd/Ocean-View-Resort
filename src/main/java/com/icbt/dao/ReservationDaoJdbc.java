package com.icbt.dao;

import com.icbt.model.Reservation;
import com.icbt.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReservationDaoJdbc implements ReservationDao {

    @Override
    public boolean existsByReservationNo(String reservationNo) {
        String sql = "SELECT 1 FROM reservations WHERE reservation_no = ? LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservationNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void save(Reservation r) {
        String sql = "INSERT INTO reservations (reservation_no, guest_name, address, contact, room_type, check_in, check_out) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getReservationNo());
            ps.setString(2, r.getGuestName());
            ps.setString(3, r.getAddress());
            ps.setString(4, r.getContact());
            ps.setString(5, r.getRoomType());
            ps.setDate(6, Date.valueOf(r.getCheckIn()));
            ps.setDate(7, Date.valueOf(r.getCheckOut()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Reservation> findByReservationNo(String reservationNo) {
        String sql = "SELECT reservation_no, guest_name, address, contact, room_type, check_in, check_out " +
                "FROM reservations WHERE reservation_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservationNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                Reservation r = new Reservation();
                r.setReservationNo(rs.getString("reservation_no"));
                r.setGuestName(rs.getString("guest_name"));
                r.setAddress(rs.getString("address"));
                r.setContact(rs.getString("contact"));
                r.setRoomType(rs.getString("room_type"));
                r.setCheckIn(rs.getDate("check_in").toLocalDate());
                r.setCheckOut(rs.getDate("check_out").toLocalDate());

                return Optional.of(r);
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
