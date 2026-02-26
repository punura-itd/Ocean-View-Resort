<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.icbt.dto.billDto" %>
<%
    billDto b = (billDto) request.getAttribute("bill");
%>
<html>
<head>
    <title>Bill - Ocean View Resort</title>
</head>
<body>

<h2>Calculate & Print Bill</h2>

<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<form action="<%=request.getContextPath()%>/billing" method="post">
    Reservation No:
    <input type="text" name="reservationNo" required/>
    <button type="submit">Generate Bill</button>
</form>

<hr/>

<% if (b != null) { %>
<h3>Bill Details</h3>
<p><b>Reservation No:</b> <%= b.getReservationNo() %></p>
<p><b>Guest Name:</b> <%= b.getGuestName() %></p>
<p><b>Room Type:</b> <%= b.getRoomType() %></p>
<p><b>Check-in:</b> <%= b.getCheckIn() %></p>
<p><b>Check-out:</b> <%= b.getCheckOut() %></p>
<p><b>Nights:</b> <%= b.getNights() %></p>
<p><b>Rate per Night:</b> LKR <%= String.format("%.2f", b.getRatePerNight()) %></p>

<hr/>
<h3>Total: LKR <%= String.format("%.2f", b.getTotal()) %></h3>

<button onclick="window.print()">Print Bill</button>
<% } %>

<br/><br/>
<a href="<%=request.getContextPath()%>/pages/dashboard.jsp">Back to Dashboard</a>

</body>
</html>