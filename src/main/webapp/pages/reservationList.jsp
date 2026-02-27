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
</head>
<body>

<h2>Reservation List</h2>

<form action="<%=request.getContextPath()%>/reservation" method="get">
    <input type="hidden" name="action" value="list"/>
    Search (Res No / Name / Phone):
    <input type="text" name="keyword" value="<%= keyword %>"/>
    <button type="submit">Search</button>
    <a href="<%=request.getContextPath()%>/reservation?action=list">Reset</a>
</form>

<br/>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Reservation No</th>
        <th>Guest Name</th>
        <th>Contact</th>
        <th>Room Type</th>
        <th>Check-in</th>
        <th>Check-out</th>
        <th>Actions</th>
    </tr>

    <%
        if (list == null || list.isEmpty()) {
    %>
    <tr>
        <td colspan="7">No reservations found.</td>
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
            <!-- View -->
            <form action="<%=request.getContextPath()%>/reservation" method="post" style="display:inline;">
                <input type="hidden" name="action" value="view"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit">View</button>
            </form>

            <!-- Edit -->
            <form action="<%=request.getContextPath()%>/reservation" method="post" style="display:inline;">
                <input type="hidden" name="action" value="edit"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit">Edit</button>
            </form>

            <!-- Cancel -->
            <form action="<%=request.getContextPath()%>/reservation" method="post" style="display:inline;"
                  onsubmit="return confirm('Cancel this reservation?');">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="reservationNo" value="<%= r.getReservationNo() %>"/>
                <button type="submit">Cancel</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<br/>
<a href="<%=request.getContextPath()%><%=request.getContextPath()%>/dashboard">Back to Dashboard</a>

</body>
</html>