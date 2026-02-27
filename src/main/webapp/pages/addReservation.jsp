<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Reservation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
<main class="container page-shell">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="brand-title mb-0">Add New Reservation</h2>
        <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
    </div>

    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-soft-success" data-auto-hide="true"><%= request.getAttribute("success") %></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-soft-danger" data-auto-hide="true"><%= request.getAttribute("error") %></div>
    <% } %>

    <div class="glass-card p-4">
        <form action="<%=request.getContextPath()%>/reservation" method="post">
            <input type="hidden" name="action" value="add"/>

            <p class="text-secondary">Reservation No (Auto Generated): <strong>Will be assigned after saving</strong></p>

            <div class="row g-3">
                <div class="col-12 col-md-6">
                    <label class="form-label">Guest Name</label>
                    <input type="text" class="form-control" name="guestName" required/>
                </div>
                <div class="col-12 col-md-6">
                    <label class="form-label">Contact (0771234567)</label>
                    <input type="text" class="form-control" name="contact" required/>
                </div>
                <div class="col-12">
                    <label class="form-label">Address</label>
                    <input type="text" class="form-control" name="address"/>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-label">Room Type</label>
                    <select class="form-select" name="roomType" required>
                        <option value="">--Select--</option>
                        <option value="STANDARD">STANDARD</option>
                        <option value="DELUXE">DELUXE</option>
                        <option value="SUITE">SUITE</option>
                    </select>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-label">Check-in</label>
                    <input type="date" class="form-control" name="checkIn" required/>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-label">Check-out</label>
                    <input type="date" class="form-control" name="checkOut" required/>
                </div>
            </div>

            <div class="mt-4 d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save Reservation</button>
                <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Back to Dashboard</a>
            </div>
        </form>
    </div>
</main>

<script src="../assets/js/app.js"></script>
</body>
</html>
