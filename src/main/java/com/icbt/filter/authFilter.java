package com.icbt.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class authFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // no-op
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Allow login page, auth servlet, and static assets without session
        boolean isPublic =
                path.equals(contextPath + "/") ||
                        path.contains("/pages/login.jsp") ||
                        path.startsWith(contextPath + "/auth") ||
                        path.startsWith(contextPath + "/assets/");

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (!loggedIn) {
            res.sendRedirect(contextPath + "/pages/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // no-op
    }
}