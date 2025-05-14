<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
  </head>
  <body>
    <div class="container">
      <footer
        class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top"
      >
        <div class="col-md-4 d-flex align-items-center">
          <a
            href="/"
            class="mb-3 me-2 mb-md-0 text-body-secondary text-decoration-none lh-1"
            aria-label="Bootstrap"
          >
            <svg class="bi" width="30" height="24" aria-hidden="true">
              <use xlink:href="#bootstrap"></use>
            </svg>
          </a>
          <span class="mb-3 mb-md-0 text-body-secondary"
            >© <span id="currentYear"></span> Pham Thanh Hieu, UTE</span
          >
        </div>
        <!-- <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
          <li class="ms-3">
            <a class="text-body-secondary" href="#" aria-label="Instagram"
              ><svg class="bi" width="24" height="24" aria-hidden="true">
                <use xlink:href="#instagram"></use></svg></a>
          </li>
          <li class="ms-3">
            <a class="text-body-secondary" href="#" aria-label="Facebook"><svg class="bi" width="24" height="24">
                <use xlink:href="#facebook"></use></svg></a>
          </li>
        </ul> -->
      </footer>
    </div>
    <script>
      document.getElementById("currentYear").innerText = new Date().getFullYear();
    </script>
  </body>
</html>
