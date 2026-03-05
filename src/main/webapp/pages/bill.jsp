<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.icbt.dto.billDto" %>
<%
    billDto b = (billDto) request.getAttribute("bill");
%>

<html>
<head>
    <title>Bill - Ocean View Resort</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css" />

</head>

<body>

<main class="container page-shell">

    <div class="d-flex flex-column flex-md-row justify-content-between gap-2 align-items-md-center mb-3">
        <h2 class="brand-title mb-0">Calculate & Print Bill</h2>
        <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
    </div>

    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-soft-success" data-auto-hide="true">
        <%= request.getAttribute("success") %>
    </div>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-soft-danger" data-auto-hide="true">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <!-- Search Form -->

    <div class="glass-card p-3 mb-3">

        <form action="<%=request.getContextPath()%>/billing"
              method="post"
              class="row g-2 align-items-end"
              onsubmit="this.querySelector('button[type=submit]').disabled=true;">

            <div class="col-12 col-md-5">
                <label class="form-label">Reservation No</label>
                <input type="text" class="form-control" name="reservationNo"/>
            </div>

            <div class="col-12 col-md-5">
                <label class="form-label">OR Phone</label>
                <input type="text" class="form-control" name="phone"/>
            </div>

            <div class="col-12 col-md-2">
                <button type="submit" class="btn btn-primary w-100">
                    Generate Bill
                </button>
            </div>

        </form>

    </div>

    <% if (b != null) { %>

    <div class="glass-card p-4 result-block invoice">

        <!-- Resort Header -->

        <div class="text-center mb-4">
            <h2 class="mb-1">Ocean View Resort</h2>
            <p class="mb-0">Galle, Sri Lanka</p>
            <p class="mb-0">Phone: +94 91 1234567</p>
            <hr>
        </div>

        <!-- Invoice Info -->

        <div class="row mb-4">

            <div class="col-md-6">
                <strong>Invoice No:</strong> <%= b.getInvoiceNo() %><br>
                <strong>Guest Name:</strong> <%= b.getGuestName() %><br>
                <strong>Reservation No:</strong> <%= b.getReservationNo() %><br>
                <strong>Room Type:</strong> <%= b.getRoomType() %>
            </div>

            <div class="col-md-6 text-md-end">
                <strong>Invoice Date:</strong> <%= java.time.LocalDate.now() %><br>
                <strong>Check-in:</strong> <%= b.getCheckIn() %><br>
                <strong>Check-out:</strong> <%= b.getCheckOut() %>
            </div>

        </div>

        <!-- Billing Table -->

        <table class="table table-bordered">

            <thead class="table-light">
            <tr>
                <th>Description</th>
                <th class="text-center">Nights</th>
                <th class="text-end">Rate (LKR)</th>
                <th class="text-end">Amount (LKR)</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td><%= b.getRoomType() %> Room Stay</td>
                <td class="text-center"><%= b.getNights() %></td>
                <td class="text-end"><%= String.format("%.2f", b.getRatePerNight()) %></td>
                <td class="text-end"><%= String.format("%.2f", b.getTotal()) %></td>
            </tr>
            </tbody>

        </table>

        <!-- Totals -->

        <div class="text-end mt-3">

            <p>Subtotal: LKR <%= String.format("%.2f", b.getTotal()) %></p>

            <p>Tax (10%): LKR <%= String.format("%.2f", b.getTax()) %></p>

            <p>Service Charge (5%): LKR <%= String.format("%.2f", b.getServiceCharge()) %></p>

            <hr>

            <h4>Grand Total: LKR <%= String.format("%.2f", b.getGrandTotal()) %></h4>

        </div>

        <!-- Footer -->

        <div class="text-center mt-4">
            <p class="text-muted">Thank you for staying with Ocean View Resort</p>
        </div>

        <!-- Print Button -->

        <div class="text-center mt-3 no-print">

            <button type="button"
                    class="btn btn-primary"
                    onclick="this.disabled=true; window.print();">
                Print Bill
            </button>

        </div>

    </div>

    <% } %>

</main>

<script src="<%=request.getContextPath()%>/assets/js/app.js"></script>

</body>
</html>