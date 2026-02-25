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

<ul>
    <li><a href="<%=request.getContextPath()%>/pages/addReservation.jsp">Add Reservation</a></li>
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