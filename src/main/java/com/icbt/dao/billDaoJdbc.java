package com.icbt.dao;

import com.icbt.dto.billDto;
import com.icbt.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class billDaoJdbc implements billDao {

    @Override
    public void save(billDto b) {
        String sql = "INSERT INTO bills(reservation_no, nights, rate_per_night, total) VALUES(?,?,?,?) " +
                "ON DUPLICATE KEY UPDATE nights=VALUES(nights), rate_per_night=VALUES(rate_per_night), total=VALUES(total)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getReservationNo());
            ps.setInt(2, (int) b.getNights());
            ps.setDouble(3, b.getRatePerNight());
            ps.setDouble(4, b.getTotal());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to save bill: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<billDto> findByReservationNo(String reservationNo) {
        String sql = "SELECT reservation_no, nights, rate_per_night, total, created_at FROM bills WHERE reservation_no=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservationNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                billDto b = new billDto();
                b.setReservationNo(rs.getString("reservation_no"));
                b.setNights(rs.getInt("nights"));
                b.setRatePerNight(rs.getDouble("rate_per_night"));
                b.setTotal(rs.getDouble("total"));
                // created_at optional (only if you add field in DTO)
                return Optional.of(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}