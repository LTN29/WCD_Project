<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Đăng ký</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/auth/css/register.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script>
	function togglePassword(id, iconId) {
		var input = document.getElementById(id);
		var icon = document.getElementById(iconId);
		if (input.type === "password") {
			input.type = "text";
			icon.classList.remove("fa-eye");
			icon.classList.add("fa-eye-slash");
		} else {
			input.type = "password";
			icon.classList.remove("fa-eye-slash");
			icon.classList.add("fa-eye");
		}
	}

	function validateForm() {
		var username = document.forms[0]["username"].value.trim();
		var pw1 = document.getElementById("password").value.trim();
		var pw2 = document.getElementById("confirmPassword").value.trim();
		var errorText = document.getElementById("jsError");

		if (username === "") {
			errorText.innerText = "Tên đăng nhập không được để trống!";
			return false;
		}

		if (pw1.length < 6 || !/[A-Z]/.test(pw1)) {
			errorText.innerText = "Mật khẩu phải từ 6 ký tự và có ít nhất 1 chữ in hoa!";
			return false;
		}

		if (pw1 !== pw2) {
			errorText.innerText = "Mật khẩu xác nhận không khớp!";
			return false;
		}

		errorText.innerText = "";
		return true;
	}
</script>
</head>

<body class="login-page">
	<div class="login-container">
		<h1>TRANG ĐĂNG KÝ</h1>
		<form method="post"
			action="${pageContext.request.contextPath}/register"
			onsubmit="return validateForm()">
			<input type="hidden" name="action" value="register" />

			<h3>Vui lòng điền thông tin</h3>

			<div class="input-box">
				<i class="fas fa-user"></i> <input type="text" name="username"
					placeholder="Tên đăng nhập" required />
			</div>

			<div class="input-box">
				<i class="fas fa-lock"></i> <input type="password" name="password"
					id="password" placeholder="Mật khẩu (tối thiểu 6 ký tự, 1 chữ hoa)"
					required /> <span class="eye-container"
					onclick="togglePassword('password', 'toggleIcon1')"> <i
					class="fas fa-eye" id="toggleIcon1"></i>
				</span>
			</div>

			<div class="input-box">
				<i class="fas fa-lock"></i> <input type="password"
					name="confirmPassword" id="confirmPassword"
					placeholder="Nhập lại mật khẩu" required /> <span
					class="eye-container"
					onclick="togglePassword('confirmPassword', 'toggleIcon2')">
					<i class="fas fa-eye" id="toggleIcon2"></i>
				</span>
			</div>


			<button type="submit" class="btn-login">Đăng ký</button>

			<c:if test="${not empty sessionScope.success}">
				<p class="success">${sessionScope.success}</p>
				<c:remove var="success" scope="session" />
			</c:if>

			<p class="error" id="jsError"></p>

			<div class="footer-note">
				<p>
					Đã có tài khoản? <a href="/WCD1_Test1/login"
						style="color: lightgreen">Đăng nhập</a>
				</p>
			</div>
		</form>
	</div>
</body>
</html>
