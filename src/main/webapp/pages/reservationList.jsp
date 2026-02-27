<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icbt.model.reservation" %>

<%
    List<reservation> list = (List<reservation>) request.getAttribute("reservations");
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) keyword = "";
%>

<html>
<head>
    <title>Reservation List</title>
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
        <h2 class="brand-title mb-0">Reservation List</h2>
        <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
    </div>

    <div class="glass-card p-3 mb-3">
        <form action="<%=request.getContextPath()%>/reservation" method="get" class="row g-2 align-items-end">
            <input type="hidden" name="action" value="list"/>
            <div class="col-12 col-md-8 col-lg-9">
                <label class="form-label">Search (Res No / Name / Phone)</label>
                <input type="text" class="form-control" name="keyword" value="<%= keyword %>"/>
            </div>
            <div class="col-6 col-md-2 col-lg-1">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
            <div class="col-6 col-md-2 col-lg-2">
                <a href="<%=request.getContextPath()%>/reservation?action=list" class="btn btn-outline-secondary w-100">Reset</a>
            </div>
        </form>
    </div>

    <div class="glass-card overflow-auto">
        <table class="table table-modern table-hover">
            <thead>
            <tr>
                <th>Reservation No</th>
                <th>Guest Name</th>
                <th>Contact</th>
                <th>Room Type</th>
                <th>Check-in</th>
                <th>Check-out</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

    <%
        if (list == null || list.isEmpty()) {
    %>
    <tr>
        <td colspan="7" class="text-center py-4">No reservations found.</td>
    </tr>
    <%
    } else {
        for (reservation r : list) {
    %>
    <tr>
        <td><%= r.getReservationNo() %></td>
        <td><%= r.getGuestName() %></td>
        <td><%= r.getContact() %></td>
        <td><%= r.getRoomType() %></td>
        <td><%= r.getCheckIn() %></td>
        <td><%= r.getCheckOut() %></td>
        <td>
            <form action="<%=request.getContextPath()%>/reservation" method="post" class="inline-form">
                <input type="hidden" name="action" value="view"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit" class="btn btn-sm btn-outline-primary">View</button>
            </form>

            <form action="<%=request.getContextPath()%>/reservation" method="post" class="inline-form">
                <input type="hidden" name="action" value="edit"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit" class="btn btn-sm btn-outline-warning">Edit</button>
            </form>

            <form action="<%=request.getContextPath()%>/reservation" method="post" class="inline-form"
                  onsubmit="return confirm('Cancel this reservation?');">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit" class="btn btn-sm btn-outline-danger">Cancel</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
            </tbody>
        </table>
    </div>
</main>

<script src="../assets/js/app.js"></script>
</body>
</html>
