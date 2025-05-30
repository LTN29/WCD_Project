<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Đăng nhập</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/auth/css/auth.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script>
	function togglePassword() {
		const pwdInput = document.getElementById("password");
		const eyeIcon = document.querySelector(".toggle-password");
		const isHidden = pwdInput.type === "password";
		pwdInput.type = isHidden ? "text" : "password";
		eyeIcon.classList.toggle("fa-eye-slash", isHidden);
		eyeIcon.classList.toggle("fa-eye", !isHidden);
	}
</script>

</head>
<body class="login-page">
	<div class="login-container">
		<h1>TRANG ĐĂNG NHẬP</h1>
		<form action="auth" method="post">
			<input type="hidden" name="action" value="login" />
			<h3>Vui lòng đăng nhập</h3>

			<div class="input-box">
				<i class="fas fa-user"></i> <input type="text" name="username"
					placeholder="Tên đăng nhập hoặc Email" required />
			</div>

			<div class="input-box">
				<i class="fas fa-lock"></i> <input type="password" id="password"
					name="password" placeholder="Mật khẩu" required />
			</div>
			<div class="eye-container">
				<i class="fas fa-eye toggle-password"
					onclick="togglePassword('password')"></i>
			</div>



			<c:if test="${not empty error}">
				<p class="error">${error}</p>
			</c:if>

			<div class="forgot">
				<a href="#">Quên mật khẩu?</a>
			</div>

			<button type="submit" class="btn-login">Đăng nhập</button>

			<div class="footer-note">
				<p>
					Bạn chưa có tài khoản? <a href="register.jsp"
						style="color: lightgreen">Đăng ký</a>
				</p>
			</div>
		</form>
	</div>
</body>
</html>
