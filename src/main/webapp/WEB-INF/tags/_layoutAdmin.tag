<%@ tag language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin - DashboaRd</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/fontawesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/templatemo-style.css">
</head>

<body id="reportsPage">
	<div class="" id="home">
		<nav class="navbar navbar-expand-xl">
			<div class="container h-100">
				<a class="navbar-brand" href="/WCD1_Test1/Admin">
					<h1 class="tm-site-title mb-0">Product Admin</h1>
				</a>
				<button class="navbar-toggler ml-auto mr-0" type="button"
					data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-bars tm-nav-icon"></i>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mx-auto h-100">
						<li class="nav-item"><a class="nav-link active"
							href="${pageContext.request.contextPath}/admin/story"> <i class="fas fa-tachometer-alt"></i>
								Stories <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="${pageContext.request.contextPath}/admin/author"> <i class="fas fa-tachometer-alt"></i>
								Authors <span class="sr-only">(current)</span>
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
						<li class="nav-item"><a class="nav-link d-block"
							href="login.html"> Admin, <b>Logout</b>
						</a></li>
					</ul>
				</div>
			</div>

		</nav>
		<div class="container">
			<div class="row">
				<div class="col">
					<p class="text-white mt-5 mb-5">
						Welcome back, <b>Admin</b>
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
					<p class="text-center text-white mb-0 px-4 small">
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
</body>

</html>