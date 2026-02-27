package com.icbt.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class DBConnectionTest {

    @AfterEach
    void resetConnectionField() throws Exception {
        Field field = DBConnection.class.getDeclaredField("connection");
        field.setAccessible(true);

        Connection existing = (Connection) field.get(null);
        if (existing != null) {
            try {
                existing.close();
            } catch (Exception ignored) {
            }
        }

        field.set(null, null);
    }

    @Test
    void getConnection_shouldCreateOnce_andReuseWhenOpen() throws Exception {
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.isClosed()).thenReturn(false);

        try (MockedStatic<DriverManager> dm = org.mockito.Mockito.mockStatic(DriverManager.class)) {
            dm.when(() -> DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD))
                    .thenReturn(mockConnection);

            Connection first = DBConnection.getConnection();
            Connection second = DBConnection.getConnection();

            assertSame(mockConnection, first);
            assertSame(first, second);

            dm.verify(() -> DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD), times(1));
        }
    }
}
