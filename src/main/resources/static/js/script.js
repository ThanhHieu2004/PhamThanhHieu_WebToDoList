window.addEventListener("DOMContentLoaded", () => {
  const imgBtn = document.querySelector(".img__btn");
  const cont = document.querySelector(".cont");

  if (imgBtn && cont) {
    // Add click event to the entire button instead of individual spans
    imgBtn.addEventListener("click", function() {
      cont.classList.toggle("s--signup");
    });

    // 👇 Auto-trigger sign-up panel if Thymeleaf sent the flag
    if (document.body.getAttribute("data-signup") === "true") {
      cont.classList.add("s--signup");
    }
  }
});
