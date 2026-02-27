<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.icbt.model.reservation" %>
<%
    reservation r = (reservation) request.getAttribute("reservation");
%>
<html>
<head>
    <title>View Reservation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
<main class="container page-shell">
    <div class="d-flex flex-column flex-md-row justify-content-between gap-2 align-items-md-center mb-3">
        <h2 class="brand-title mb-0">View Reservation</h2>
        <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-soft-danger" data-auto-hide="true"><%= request.getAttribute("error") %></div>
    <% } %>

    <div class="glass-card p-3 mb-3">
        <form action="<%=request.getContextPath()%>/reservation" method="post" class="row g-2 align-items-end">
            <input type="hidden" name="action" value="view"/>
            <div class="col-12 col-md-5">
                <label class="form-label">Reservation No</label>
                <input type="text" class="form-control" name="reservationNo"/>
            </div>
            <div class="col-12 col-md-5">
                <label class="form-label">OR Phone</label>
                <input type="text" class="form-control" name="phone"/>
            </div>
            <div class="col-12 col-md-2">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </form>
    </div>

<% if (r != null) { %>
    <div class="glass-card p-4 result-block">
        <h4 class="mb-3">Reservation Details</h4>
        <p><strong>Reservation No:</strong> <%= r.getReservationNo() %></p>
        <p><strong>Guest Name:</strong> <%= r.getGuestName() %></p>
        <p><strong>Address:</strong> <%= r.getAddress() %></p>
        <p><strong>Contact:</strong> <%= r.getContact() %></p>
        <p><strong>Room Type:</strong> <%= r.getRoomType() %></p>
        <p><strong>Check-in:</strong> <%= r.getCheckIn() %></p>
        <p><strong>Check-out:</strong> <%= r.getCheckOut() %></p>
        <div class="mt-3">
            <form action="<%=request.getContextPath()%>/reservation" method="post" class="inline-form">
                <input type="hidden" name="action" value="edit"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit" class="btn btn-outline-warning">Edit</button>
            </form>
            <form action="<%=request.getContextPath()%>/reservation" method="post" class="inline-form"
                  onsubmit="return confirm('Are you sure you want to cancel this reservation?');">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit" class="btn btn-outline-danger">Cancel Reservation</button>
            </form>
        </div>
    </div>
<% } %>
</main>

<script src="../assets/js/app.js"></script>
</body>
</html>
