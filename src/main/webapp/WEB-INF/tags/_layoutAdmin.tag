<%@ tag language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/admin/css/fontawesome.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

  <style>
    body {
      background-color: #1e1e2f;
      color: #f0f0f0;
      font-family: 'Roboto', sans-serif;
    }

    .navbar {
      background-color: #2c2f48;
    }

    .navbar .nav-link {
      color: #f0f0f0 !important;
    }

    .navbar .nav-link:hover,
    .navbar .nav-link.active {
      color: #4fd1c5 !important;
    }

    .tm-site-title {
      color: #ffffff;
    }

    .admin-main {
      padding: 2rem 0;
    }

    .tm-footer {
      background-color: #2c2f48;
      border-top: 1px solid #444;
      color: #aaa;
      padding: 1rem 0;
    }

    .tm-footer a {
      color: #4fd1c5;
    }
  </style>
</head>

<body id="reportsPage">
  <div id="home">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg">
      <div class="container">
        <a class="navbar-brand" href="/WCD1_Test1/Admin">
          <h1 class="tm-site-title mb-0">Product Admin</h1>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
          data-target="#navbarContent" aria-controls="navbarContent"
          aria-expanded="false" aria-label="Toggle navigation">
          <i class="fas fa-bars tm-nav-icon"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
          <ul class="navbar-nav mx-auto">
            <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/admin/story"><i class="bi bi-book"></i> Stories</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/author"><i class="bi bi-pen"></i> Authors</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/chapter"><i class="bi bi-layers"></i> Chapters</a></li>
          </ul>
          <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="login.html">Admin, <b>Logout</b></a></li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="admin-main">
      <jsp:doBody />
    </main>

    <!-- Footer -->
    <footer class="tm-footer text-center">
      <p class="small mb-0">&copy; 2024 Product Admin Dashboard. Design inspired by <a href="https://templatemo.com">TemplateMo</a></p>
    </footer>
  </div>

  <!-- Scripts -->
  <script src="${pageContext.request.contextPath}/admin/js/jquery-3.3.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
</body>
</html>
