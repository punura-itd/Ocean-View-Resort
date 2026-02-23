<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Dashboard</title></head>
<body>
<h2>Dashboard</h2>
<ul>
    <li><a href="<%=request.getContextPath()%>/pages/reservation-add.jsp">Add Reservation</a></li>
    <li><a href="<%=request.getContextPath()%>/pages/reservation-view.jsp">View Reservation</a></li>
    <li><a href="<%=request.getContextPath()%>/pages/bill.jsp">Calculate Bill</a></li>
    <li><a href="<%=request.getContextPath()%>/pages/help.jsp">Help</a></li>
</ul>
</body>
</html>