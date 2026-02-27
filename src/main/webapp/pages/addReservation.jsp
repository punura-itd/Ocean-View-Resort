<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Reservation</title>
</head>
<body>

<h2>Add New Reservation</h2>

<p style="color:green;"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></p>
<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<form action="<%=request.getContextPath()%>/reservation" method="post">
    <input type="hidden" name="action" value="add"/>

    Reservation No (Auto Generated): <b>Will be assigned after saving</b><br/><br/>

    Guest Name:<br/>
    <input type="text" name="guestName" required/><br/><br/>

    Address:<br/>
    <input type="text" name="address"/><br/><br/>

    Contact (0771234567):<br/>
    <input type="text" name="contact" required/><br/><br/>

    Room Type:<br/>
    <select name="roomType" required>
        <option value="">--Select--</option>
        <option value="STANDARD">STANDARD</option>
        <option value="DELUXE">DELUXE</option>
        <option value="SUITE">SUITE</option>
    </select><br/><br/>

    Check-in:<br/>
    <input type="date" name="checkIn" required/><br/><br/>

    Check-out:<br/>
    <input type="date" name="checkOut" required/><br/><br/>

    <button type="submit">Save Reservation</button>
</form>

<br/>
<a href="<%=request.getContextPath()%>/dashboard">Back to Dashboard</a>

</body>
</html>