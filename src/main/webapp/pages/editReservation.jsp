<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.icbt.model.reservation" %>
<%
    reservation r = (reservation) request.getAttribute("reservation");
%>
<html>
<head>
    <title>Edit Reservation</title>
</head>
<body>

<h2>Edit Reservation</h2>

<p style="color:green;"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></p>
<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<% if (r != null) { %>
<form action="<%=request.getContextPath()%>/reservation" method="post">
    <input type="hidden" name="action" value="update"/>

    Reservation No:<br/>
    <input type="text" name="reservationNo" value="<%= r.getReservationNo() %>" readonly/><br/><br/>

    Guest Name:<br/>
    <input type="text" name="guestName" value="<%= r.getGuestName() %>" required/><br/><br/>

    Address:<br/>
    <input type="text" name="address" value="<%= r.getAddress() %>"/><br/><br/>

    Contact:<br/>
    <input type="text" name="contact" value="<%= r.getContact() %>" required/><br/><br/>

    Room Type:<br/>
    <select name="roomType" required>
        <option value="STANDARD" <%= "STANDARD".equalsIgnoreCase(r.getRoomType()) ? "selected" : "" %>>STANDARD</option>
        <option value="DELUXE"   <%= "DELUXE".equalsIgnoreCase(r.getRoomType()) ? "selected" : "" %>>DELUXE</option>
        <option value="SUITE"    <%= "SUITE".equalsIgnoreCase(r.getRoomType()) ? "selected" : "" %>>SUITE</option>
    </select><br/><br/>

    Check-in:<br/>
    <input type="date" name="checkIn" value="<%= r.getCheckIn() %>" required/><br/><br/>

    Check-out:<br/>
    <input type="date" name="checkOut" value="<%= r.getCheckOut() %>" required/><br/><br/>

    <button type="submit">Update Reservation</button>
</form>
<% } else { %>
<p>No reservation loaded.</p>
<% } %>

<br/>
<a href="<%=request.getContextPath()%>/pages/viewReservation.jsp">Back to View Reservation</a> |
<a href="<%=request.getContextPath()%><%=request.getContextPath()%>/dashboard">Dashboard</a>

</body>
</html>