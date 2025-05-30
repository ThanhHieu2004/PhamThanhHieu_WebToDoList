<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
          <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
          </a>
    
          <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" th:class="${isFinishedTasksPage == false} ? 'nav-link px-2 link-primary fw-bold' : 'nav-link px-2 link-dark'">Công việc cần làm</a></li>
            <li><a href="/finished-tasks" th:class="${isFinishedTasksPage == true} ? 'nav-link px-2 link-primary fw-bold' : 'nav-link px-2 link-dark'">Công việc đã hoàn thành</a></li>
            <li><a href="/bao-cao" class="nav-link px-2 link-dark" target="_blank">Tải xuống báo cáo</a></li>
          </ul>
    
          <div class="col-md-3 text-end">
            <button type="button" class="btn btn-outline-primary me-2"><a href="/login">Đăng xuất</a></button>
            <!-- <a href="/login"><button type="button" class="btn btn-primary">Đăng kí</button></a> -->
          </div>
        </header>
      </div>
</body>
</html>