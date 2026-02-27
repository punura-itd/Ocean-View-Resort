<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Help - Ocean View Resort</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
<main class="container page-shell">
    <div class="d-flex flex-column flex-md-row justify-content-between gap-2 align-items-md-center mb-3">
        <h2 class="brand-title mb-0">Help & Usage</h2>
        <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
    </div>

    <div class="glass-card p-4">
        <h5>Common Actions</h5>
        <ul class="mb-4">
            <li><strong>Add Reservation:</strong> Open Add Reservation, complete guest details, then submit.</li>
            <li><strong>Find Reservation:</strong> Use View Reservation or Reservation List search.</li>
            <li><strong>Edit/Cancel:</strong> Search reservation first, then choose Edit or Cancel.</li>
            <li><strong>Generate Bill:</strong> Use reservation number or phone and print from bill page.</li>
        </ul>

        <h5>Data Entry Tips</h5>
        <ul class="mb-4">
            <li>Use a valid phone format such as <code>0771234567</code>.</li>
            <li>Ensure check-out date is later than check-in date.</li>
            <li>Keep reservation numbers exactly as generated (for example, <code>RES-1001</code>).</li>
        </ul>

        <h5>Need Support?</h5>
        <p class="mb-0 text-secondary">If an error appears, retry with the reservation number and verify all fields before submitting again.</p>
    </div>
</main>

<script src="../assets/js/app.js"></script>
</body>
</html>
