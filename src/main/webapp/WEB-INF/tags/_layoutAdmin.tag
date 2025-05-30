<<<<<<< HEAD
<%@ tag language="java" pageEncoding="UTF-8" %>
=======
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
>>>>>>> 7c4b6419f8e2c18e6c8aed1ec04ee949a94363ca
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

<<<<<<< HEAD
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
=======
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mx-auto h-100">
						<li class="nav-item"><a class="nav-link active"
							href="${pageContext.request.contextPath}/admin/story"> <i
								class="fas fa-tachometer-alt"></i> Stories <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="${pageContext.request.contextPath}/admin/author"> <i
								class="fas fa-tachometer-alt"></i> Authors <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="far fa-file-alt"></i> <span>
									Reports <i class="fas fa-angle-down"></i>
							</span>
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Daily Report</a> <a
									class="dropdown-item" href="#">Weekly Report</a> <a
									class="dropdown-item" href="#">Yearly Report</a>
							</div></li>
						<li class="nav-item"><a class="nav-link" href="products.html">
								<i class="fas fa-shopping-cart"></i> Products
						</a></li>

						<li class="nav-item"><a class="nav-link" href="accounts.html">
								<i class="far fa-user"></i> Accounts
						</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-cog"></i> <span>
									Settings <i class="fas fa-angle-down"></i>
							</span>
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Profile</a> <a
									class="dropdown-item" href="#">Billing</a> <a
									class="dropdown-item" href="#">Customize</a>
							</div></li>
					</ul>
					<ul class="navbar-nav">
						<c:choose>
							<c:when test="${not empty sessionScope.user}">
								<li class="nav-item"><a class="nav-link d-block"
									href="${pageContext.request.contextPath}/logout">
										${sessionScope.user.name}, <b>Logout</b>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link d-block"
									href="${pageContext.request.contextPath}/login"> Đăng nhập
								</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
			</div>

		</nav>
		<div class="container">
			<div class="row">
				<div class="col">
					<p class="text-black mt-5 mb-5">
						Welcome back, <b>${sessionScope.user.name}</b>
					</p>

				</div>
			</div>
			<!-- ====== MAIN ====== -->
			<main class="admin-main">
				<jsp:doBody />
			</main>

			<!-- ====== FOOTER ====== -->
			<footer class="tm-footer row tm-mt-small">
				<div class="col-12 font-weight-light">
					<p class="text-center text-black mb-0 px-4 small">
						Copyright &copy; <b>2018</b> All rights reserved. Design: <a
							rel="nofollow noopener" href="https://templatemo.com"
							class="tm-footer-link">Template Mo</a>
					</p>
				</div>
			</footer>
		</div>

		<script
			src="${pageContext.request.contextPath}/admin/js/jquery-3.3.1.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/admin/js/moment.min.js"></script>
		<script src="${pageContext.request.contextPath}/admin/js/Chart.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/admin/js/tooplate-scripts.js"></script>
		<script>
			Chart.defaults.global.defaultFontColor = 'white';
			let ctxLine, ctxBar, ctxPie, optionsLine, optionsBar, optionsPie, configLine, configBar, configPie, lineChart;
			barChart, pieChart;
			// DOM is ready
			$(function() {
				drawLineChart(); // Line Chart
				drawBarChart(); // Bar Chart
				drawPieChart(); // Pie Chart

				$(window).resize(function() {
					updateLineChart();
					updateBarChart();
				});
			})
		</script>
>>>>>>> 7c4b6419f8e2c18e6c8aed1ec04ee949a94363ca
</body>
</html>
