<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.icbt.dto.billDto" %>
<%
    billDto b = (billDto) request.getAttribute("bill");
%>
<html>
<head>
    <title>Bill - Ocean View Resort</title>
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
        <h2 class="brand-title mb-0">Calculate & Print Bill</h2>
        <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-soft-danger" data-auto-hide="true"><%= request.getAttribute("error") %></div>
    <% } %>

    <div class="glass-card p-3 mb-3">
        <form action="<%=request.getContextPath()%>/billing" method="post" class="row g-2 align-items-end">
            <div class="col-12 col-md-5">
                <label class="form-label">Reservation No</label>
                <input type="text" class="form-control" name="reservationNo"/>
            </div>
            <div class="col-12 col-md-5">
                <label class="form-label">OR Phone</label>
                <input type="text" class="form-control" name="phone"/>
            </div>
            <div class="col-12 col-md-2">
                <button type="submit" class="btn btn-primary w-100">Generate Bill</button>
            </div>
        </form>
    </div>

<% if (b != null) { %>
    <div class="glass-card p-4 result-block">
        <h4 class="mb-3">Bill Details</h4>
        <p><strong>Reservation No:</strong> <%= b.getReservationNo() %></p>
        <p><strong>Guest Name:</strong> <%= b.getGuestName() %></p>
        <p><strong>Room Type:</strong> <%= b.getRoomType() %></p>
        <p><strong>Check-in:</strong> <%= b.getCheckIn() %></p>
        <p><strong>Check-out:</strong> <%= b.getCheckOut() %></p>
        <p><strong>Nights:</strong> <%= b.getNights() %></p>
        <p><strong>Rate per Night:</strong> LKR <%= String.format("%.2f", b.getRatePerNight()) %></p>
        <hr/>
        <h3 class="mb-3">Total: LKR <%= String.format("%.2f", b.getTotal()) %></h3>
        <button type="button" class="btn btn-primary" onclick="window.print()">Print Bill</button>
    </div>
<% } %>
</main>

<script src="../assets/js/app.js"></script>
</body>
</html>
