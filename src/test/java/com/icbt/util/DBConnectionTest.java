package com.icbt.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void testConnection() {
        try (Connection conn = DBConnection.getConnection()) {

            assertNotNull(conn);
            assertFalse(conn.isClosed());

            System.out.println("✅ Database connection successful");

        } catch (Exception e) {
            fail("Connection failed: " + e.getMessage());
        }
    }
}