
document.addEventListener("DOMContentLoaded", function () {
    var sourceAlerts = document.querySelectorAll("[data-auto-hide='true']");
    if (!sourceAlerts.length) return;

    var stack = document.createElement("div");
    stack.className = "popup-alert-stack";
    stack.setAttribute("aria-live", "polite");
    stack.setAttribute("aria-atomic", "true");
    document.body.appendChild(stack);

    sourceAlerts.forEach(function (alert, index) {
        var isSuccess = alert.classList.contains("alert-soft-success");
        var typeClass = isSuccess ? "popup-alert-success" : "popup-alert-error";
        var title = isSuccess ? "Success" : "Error";

        var popup = document.createElement("div");
        popup.className = "popup-alert " + typeClass;
        popup.setAttribute("role", "alert");

        popup.innerHTML =
            "<div class='popup-alert-icon'>" + (isSuccess ? "&#10003;" : "!") + "</div>" +
            "<div class='popup-alert-content'>" +
                "<div class='popup-alert-title'>" + title + "</div>" +
                "<div class='popup-alert-message'></div>" +
            "</div>" +
            "<button type='button' class='popup-alert-close' aria-label='Close'>&times;</button>";

        popup.querySelector(".popup-alert-message").textContent = alert.textContent.trim();
        stack.appendChild(popup);

        var closeBtn = popup.querySelector(".popup-alert-close");
        closeBtn.addEventListener("click", function () {
            dismissPopup(popup);
        });

        setTimeout(function () {
            popup.classList.add("is-visible");
        }, 80 * index);

        setTimeout(function () {
            dismissPopup(popup);
        }, 4200 + index * 150);

        alert.remove();
    });

    function dismissPopup(popup) {
        if (!popup || popup.classList.contains("is-closing")) return;
        popup.classList.add("is-closing");
        popup.classList.remove("is-visible");
        setTimeout(function () {
            if (popup && popup.parentNode) {
                popup.parentNode.removeChild(popup);
            }
            if (stack && !stack.children.length) {
                stack.remove();
            }
        }, 260);
    }
});

// Reservation date validation
document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("#addReservationForm");
    if (!form) return;

    const checkIn = form.querySelector("input[name='checkIn']");
    const checkOut = form.querySelector("input[name='checkOut']");
    if (!checkIn || !checkOut) return;

    // Optional UX: block selecting checkout before checkin
    checkIn.addEventListener("change", function () {
        checkOut.min = checkIn.value || "";
        if (checkOut.value && checkOut.value <= checkIn.value) {
            checkOut.value = "";
        }
    });

    form.addEventListener("submit", function (e) {
        const inVal = checkIn.value;
        const outVal = checkOut.value;

        if (!inVal || !outVal) return;

        // Safer than string compare: convert to Date
        const inDate = new Date(inVal + "T00:00:00");
        const outDate = new Date(outVal + "T00:00:00");

        if (outDate <= inDate) {
            e.preventDefault();
            alert("Check-out date must be after check-in date.");
            checkOut.focus();
        }
    });
});

