<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.icbt.model.reservation" %>
<%
    reservation r = (reservation) request.getAttribute("reservation");
%>
<html>
<head>
    <title>View Reservation</title>
</head>
<body>

<h2>View Reservation</h2>

<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<form action="<%=request.getContextPath()%>/reservation" method="post">
    <input type="hidden" name="action" value="view"/>
    Reservation No:
    <input type="text" name="reservationNo" required/>
    <button type="submit">Search</button>
</form>

<hr/>

<% if (r != null) { %>
<h3>Reservation Details</h3>
<p><b>Reservation No:</b> <%= r.getReservationNo() %></p>
<p><b>Guest Name:</b> <%= r.getGuestName() %></p>
<p><b>Address:</b> <%= r.getAddress() %></p>
<p><b>Contact:</b> <%= r.getContact() %></p>
<p><b>Room Type:</b> <%= r.getRoomType() %></p>
<p><b>Check-in:</b> <%= r.getCheckIn() %></p>
<p><b>Check-out:</b> <%= r.getCheckOut() %></p>
<% } %>

<br/>
<a href="<%=request.getContextPath()%>/pages/dashboard.jsp">Back to Dashboard</a>

</body>
</html>