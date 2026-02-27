document.addEventListener("DOMContentLoaded", function () {
    var alerts = document.querySelectorAll("[data-auto-hide='true']");

    alerts.forEach(function (alert) {
        setTimeout(function () {
            alert.classList.add("d-none");
        }, 4000);
    });
});
