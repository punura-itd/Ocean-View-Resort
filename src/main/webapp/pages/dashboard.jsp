<%@ page import="com.icbt.dto.dashboardStatsDto" %>
<%
    dashboardStatsDto stats = (dashboardStatsDto) request.getAttribute("stats");
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String user = (String) session.getAttribute("user");
%>
<html>
<head>
    <title>Dashboard - Ocean View Resort</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
<main class="container page-shell">
    <div class="glass-card p-3 p-md-4 mb-4">
        <div class="d-flex flex-column flex-md-row gap-3 justify-content-between align-items-md-center">
            <div>
                <span class="hero-badge">Dashboard</span>
                <h2 class="brand-title mt-2 mb-1">Ocean View Resort</h2>
                <p class="mb-0 text-secondary">Welcome, <strong><%= user %></strong></p>
            </div>
            <form action="<%=request.getContextPath()%>/logout" method="post" class="m-0">
                <button type="submit" class="btn btn-outline-danger">Logout</button>
            </form>
        </div>
    </div>

    <h4 class="mb-3">Quick Stats</h4>

<% if (stats != null) { %>
    <div class="row g-3 mb-4">
        <div class="col-6 col-lg-4">
            <div class="kpi-card p-3">
                <div class="kpi-label">Total Reservations</div>
                <div class="kpi-value"><%= stats.getTotalReservations() %></div>
            </div>
        </div>
        <div class="col-6 col-lg-4">
            <div class="kpi-card p-3">
                <div class="kpi-label">Today Check-ins</div>
                <div class="kpi-value"><%= stats.getTodayCheckIns() %></div>
            </div>
        </div>
        <div class="col-6 col-lg-4">
            <div class="kpi-card p-3">
                <div class="kpi-label">Today Check-outs</div>
                <div class="kpi-value"><%= stats.getTodayCheckOuts() %></div>
            </div>
        </div>
        <div class="col-6 col-lg-4">
            <div class="kpi-card p-3">
                <div class="kpi-label">Total Bills</div>
                <div class="kpi-value"><%= stats.getTotalBills() %></div>
            </div>
        </div>
        <div class="col-12 col-lg-8">
            <div class="kpi-card p-3">
                <div class="kpi-label">Total Revenue</div>
                <div class="kpi-value">LKR <%= String.format("%.2f", stats.getTotalRevenue()) %></div>
            </div>
        </div>
    </div>
<% } else { %>
    <div class="alert alert-soft-danger">No stats available.</div>
<% } %>

    <div class="row g-3">
        <div class="col-12 col-md-6 col-xl-4">
            <a class="text-decoration-none" href="<%=request.getContextPath()%>/pages/addReservation.jsp">
                <div class="glass-card p-3 h-100">
                    <h5 class="mb-1">Add Reservation</h5>
                    <p class="mb-0 text-secondary">Create a new booking quickly.</p>
                </div>
            </a>
        </div>
        <div class="col-12 col-md-6 col-xl-4">
            <a class="text-decoration-none" href="<%=request.getContextPath()%>/reservation?action=list">
                <div class="glass-card p-3 h-100">
                    <h5 class="mb-1">Reservation List</h5>
                    <p class="mb-0 text-secondary">Search and manage all reservations.</p>
                </div>
            </a>
        </div>
        <div class="col-12 col-md-6 col-xl-4">
            <a class="text-decoration-none" href="<%=request.getContextPath()%>/pages/viewReservation.jsp">
                <div class="glass-card p-3 h-100">
                    <h5 class="mb-1">View Reservation</h5>
                    <p class="mb-0 text-secondary">Lookup by reservation number or phone.</p>
                </div>
            </a>
        </div>
        <div class="col-12 col-md-6 col-xl-4">
            <a class="text-decoration-none" href="<%=request.getContextPath()%>/pages/bill.jsp">
                <div class="glass-card p-3 h-100">
                    <h5 class="mb-1">Calculate Bill</h5>
                    <p class="mb-0 text-secondary">Generate and print invoices instantly.</p>
                </div>
            </a>
        </div>
        <div class="col-12 col-md-6 col-xl-4">
            <a class="text-decoration-none" href="<%=request.getContextPath()%>/pages/help.jsp">
                <div class="glass-card p-3 h-100">
                    <h5 class="mb-1">Help</h5>
                    <p class="mb-0 text-secondary">System guidance and user support tips.</p>
                </div>
            </a>
        </div>
    </div>
</main>

<script src="../assets/js/app.js"></script>
</body>
</html>
