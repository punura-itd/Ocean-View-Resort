package com.icbt.service;

public class AuthServiceTest {
    @Test
    void login_shouldReturnTrue_whenDaoValidates() {
        UserDao dao = mock(UserDao.class);
        when(dao.validateCredentials("admin", "1234")).thenReturn(true);

        AuthService svc = new AuthService(dao);

        assertTrue(svc.login("admin", "1234"));
        verify(dao, times(1)).validateCredentials("admin", "1234");
    }

    @Test
    void login_shouldReturnFalse_whenBlankUsernameOrPassword() {
        UserDao dao = mock(UserDao.class);
        AuthService svc = new AuthService(dao);

        assertFalse(svc.login("", "1234"));
        assertFalse(svc.login("admin", ""));
        verify(dao, never()).validateCredentials(anyString(), anyString());
    }
}
