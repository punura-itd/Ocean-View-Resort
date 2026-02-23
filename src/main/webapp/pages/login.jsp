<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - Ocean View Resort</title>
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
<h2>Ocean View Resort - Login</h2>

<form action="<%=request.getContextPath()%>/auth" method="post">
    <label>Username</label><br/>
    <input type="text" name="username" required /><br/><br/>

    <label>Password</label><br/>
    <input type="password" name="password" required /><br/><br/>

    <button type="submit">Login</button>
</form>

<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
</body>
</html>