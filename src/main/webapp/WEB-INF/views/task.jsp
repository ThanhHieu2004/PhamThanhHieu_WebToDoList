<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
      crossorigin="anonymous"
    />
    <!-- Font Awesome -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
      rel="stylesheet"
    />
    <!-- MDB -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/9.0.0/mdb.min.css"
      rel="stylesheet"
    />
    <style>
      .divider:after,
      .divider:before {
        content: "";
        flex: 1;
        height: 1px;
        background: #eee;
      }
    </style>
  </head>
  <body>
    <jsp:include page="_header.jsp" />
    <section class="vh-100" style="background-color: #3da2c3">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col col-lg-8 col-xl-6">
            <div class="card rounded-3">
              <div class="card-body p-4">
                <p class="mb-2">
                  <span class="h2 me-2">Team Meeting</span>
                  <span class="badge bg-danger">checklist</span>
                </p>
                <p class="text-muted pb-2">04/01/2020 • ML - 1321</p>

                <ul class="list-group rounded-0">
                  <li
                    class="list-group-item border-0 d-flex align-items-center ps-0"
                  >
                    <input
                      class="form-check-input me-3"
                      type="checkbox"
                      value=""
                      aria-label="..."
                      checked
                    />
                    <s>Task list and assignments</s>
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center ps-0"
                  >
                    <input
                      class="form-check-input me-3"
                      type="checkbox"
                      value=""
                      aria-label="..."
                    />
                    Set due date and assignments
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center ps-0"
                  >
                    <input
                      class="form-check-input me-3"
                      type="checkbox"
                      value=""
                      aria-label="..."
                    />
                    Remove duplicate tasks and stories
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center ps-0"
                  >
                    <input
                      class="form-check-input me-3"
                      type="checkbox"
                      value=""
                      aria-label="..."
                    />
                    Update the userflow and stories
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center ps-0"
                  >
                    <input
                      class="form-check-input me-3"
                      type="checkbox"
                      value=""
                      aria-label="..."
                    />
                    Adjust the components
                  </li>
                </ul>

                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center mx-3 mb-0" style="color: #a2aab7">
                    Shared with
                  </p>
                </div>

                <ul
                  class="list-group rounded-0 list-group-horizontal justify-content-center pb-2"
                >
                  <li
                    class="list-group-item border-0 d-flex align-items-center p-0"
                  >
                    <img
                      src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-8.webp"
                      alt="avatar"
                      class="rounded-circle me-n2"
                      width="45"
                    />
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center p-0"
                  >
                    <img
                      src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-3.webp"
                      alt="avatar"
                      class="rounded-circle me-n2"
                      width="45"
                    />
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center p-0"
                  >
                    <img
                      src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-5.webp"
                      alt="avatar"
                      class="rounded-circle me-n2"
                      width="45"
                    />
                  </li>
                  <li
                    class="list-group-item border-0 d-flex align-items-center p-0"
                  >
                    <img
                      src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-6.webp"
                      alt="avatar"
                      class="rounded-circle me-n2"
                      width="45"
                    />
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="_footer.jsp" />
    <!-- MDB -->
    <script
      type="text/javascript"
      src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/9.0.0/mdb.umd.min.js"
    ></script>
  </body>
</html>
