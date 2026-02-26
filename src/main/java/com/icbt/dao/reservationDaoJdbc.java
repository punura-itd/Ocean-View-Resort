package com.icbt.dao;

import com.icbt.model.reservation;
import com.icbt.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class reservationDaoJdbc implements reservationDao {

    @Override
    public Optional<reservation> findByPhone(String phone) {

        String sql = "SELECT reservation_no, guest_name, address, contact, room_type, check_in, check_out " +
                "FROM reservations WHERE contact=? ORDER BY check_in DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                reservation r = new reservation();
                r.setReservationNo(rs.getString("reservation_no"));
                r.setGuestName(rs.getString("guest_name"));
                r.setAddress(rs.getString("address"));
                r.setContact(rs.getString("contact"));
                r.setRoomType(rs.getString("room_type"));
                r.setCheckIn(rs.getDate("check_in").toLocalDate());
                r.setCheckOut(rs.getDate("check_out").toLocalDate());

                return Optional.of(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public String findLastReservationNo() {

        String sql = "SELECT reservation_no FROM reservations ORDER BY reservation_no DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("reservation_no");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean existsByReservationNo(String reservationNo) {
        String sql = "SELECT 1 FROM reservations WHERE reservation_no=? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservationNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(reservation r) {
        String sql = "UPDATE reservations SET guest_name=?, address=?, contact=?, room_type=?, check_in=?, check_out=? " +
                "WHERE reservation_no=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getGuestName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getContact());
            ps.setString(4, r.getRoomType());
            ps.setDate(5, Date.valueOf(r.getCheckIn()));
            ps.setDate(6, Date.valueOf(r.getCheckOut()));
            ps.setString(7, r.getReservationNo());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to update reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByReservationNo(String reservationNo) {
        String sql = "DELETE FROM reservations WHERE reservation_no=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservationNo);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(reservation r) {
        String sql = "INSERT INTO reservations(reservation_no, guest_name, address, contact, room_type, check_in, check_out) " +
                "VALUES(?,?,?,?,?,?,?)";

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

        } catch (Exception e) {
            throw new RuntimeException("Failed to save reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<reservation> findByReservationNo(String reservationNo) {
        String sql = "SELECT reservation_no, guest_name, address, contact, room_type, check_in, check_out " +
                "FROM reservations WHERE reservation_no=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservationNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                reservation r = new reservation();
                r.setReservationNo(rs.getString("reservation_no"));
                r.setGuestName(rs.getString("guest_name"));
                r.setAddress(rs.getString("address"));
                r.setContact(rs.getString("contact"));
                r.setRoomType(rs.getString("room_type"));
                r.setCheckIn(rs.getDate("check_in").toLocalDate());
                r.setCheckOut(rs.getDate("check_out").toLocalDate());

                return Optional.of(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}