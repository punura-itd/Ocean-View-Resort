<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - Ocean View Resort</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
<main class="container page-shell">
    <div class="row justify-content-center align-items-center min-vh-100">
        <div class="col-12 col-md-8 col-lg-5">
            <div class="glass-card p-4 p-md-5">
                <span class="hero-badge">Resort Management Portal</span>
                <h2 class="brand-title mt-3 mb-1">Ocean View Resort</h2>
                <p class="text-secondary mb-4">Sign in to manage reservations, billing, and dashboard insights.</p>

                <form action="<%=request.getContextPath()%>/auth" method="post">
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" class="form-control" name="username" required />
                    </div>

                    <div class="mb-4">
                        <label class="form-label">Password</label>
                        <input type="password" class="form-control" name="password" required />
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </form>

                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-soft-danger mt-3 mb-0" data-auto-hide="true">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</main>
<script src="../assets/js/app.js"></script>
</body>
</html>
