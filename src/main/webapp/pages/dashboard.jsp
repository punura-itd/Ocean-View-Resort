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
</head>
<body>

<h2>Ocean View Resort - Dashboard</h2>

<p>Welcome, <b><%= user %></b>!</p>
<h3>Quick Stats</h3>

<% if (stats != null) { %>
<ul>
    <li><b>Total Reservations:</b> <%= stats.getTotalReservations() %></li>
    <li><b>Today Check-ins:</b> <%= stats.getTodayCheckIns() %></li>
    <li><b>Today Check-outs:</b> <%= stats.getTodayCheckOuts() %></li>
    <li><b>Total Bills:</b> <%= stats.getTotalBills() %></li>
    <li><b>Total Revenue:</b> LKR <%= String.format("%.2f", stats.getTotalRevenue()) %></li>
</ul>
<% } else { %>
<p>No stats available.</p>
<% } %>
<ul>
    <li><a href="<%=request.getContextPath()%>/pages/addReservation.jsp">Add Reservation</a></li>
    <li><a href="<%=request.getContextPath()%>/reservation?action=list">Reservation List</a></li>
    <li><a href="<%=request.getContextPath()%>/pages/viewReservation.jsp">View Reservation</a></li>
    <li><a href="<%=request.getContextPath()%>/pages/bill.jsp">Calculate Bill</a></li>
    <li><a href="<%=request.getContextPath()%>/pages/help.jsp">Help</a></li>
</ul>

<hr/>

<form action="<%=request.getContextPath()%>/logout" method="post">
    <button type="submit">Logout</button>
</form>

</body>
</html>