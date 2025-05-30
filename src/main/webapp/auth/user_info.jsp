<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>

<client:_layoutClient>

	<main class="client-main"
		style="padding: 40px 20px; max-width: 800px; margin: auto;">
		<h2 style="margin-bottom: 30px; text-align: center;">Thông Tin
			Người Dùng</h2>

		<c:if test="${not empty sessionScope.user}">
			<form method="post"
				action="${pageContext.request.contextPath}/updateProfile">
				<div style="margin-bottom: 20px;">
					<label><b>Tên đăng nhập:</b></label> <input type="text"
						value="${sessionScope.user.userName}" readonly
						class="form-control" />
				</div>

				<div style="margin-bottom: 20px;">
					<label><b>Tên hiển thị:</b></label> <input type="text" name="name"
						value="${sessionScope.user.name}" class="form-control" required />
				</div>

				<div style="margin-bottom: 20px;">
					<label><b>Điểm:</b></label> <input type="text"
						value="${sessionScope.user.score}" readonly class="form-control" />
				</div>

				<div style="margin-bottom: 20px;">
					<label><b>Cấp độ:</b></label> <input type="text"
						value="${sessionScope.user.level}" readonly
						class="form-control" />
				</div>

				<div style="margin-bottom: 40px;">
					<button type="submit" class="btn btn-success">Cập nhật tên</button>
				</div>
			</form>

			<form method="post"
				action="${pageContext.request.contextPath}/changePassword">
				<h3>Đổi mật khẩu</h3>
				<div style="margin-bottom: 20px;">
					<label><b>Mật khẩu cũ:</b></label> <input type="password"
						name="oldPassword" class="form-control" required />
				</div>

				<div style="margin-bottom: 20px;">
					<label><b>Mật khẩu mới:</b></label> <input type="password"
						name="newPassword" class="form-control" required />
				</div>

				<div style="margin-bottom: 20px;">
					<label><b>Nhập lại mật khẩu mới:</b></label> <input type="password"
						name="confirmPassword" class="form-control" required />
				</div>

				<div style="margin-bottom: 40px;">
					<button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
				</div>
			</form>

			<form method="post"
				action="${pageContext.request.contextPath}/deleteAccount"
				onsubmit="return confirm('Bạn chắc chắn muốn xóa tài khoản? Hành động này không thể hoàn tác!');">
				<button type="submit" class="btn btn-danger">Xóa tài khoản</button>
			</form>
		</c:if>

		<c:if test="${empty sessionScope.user}">
			<p>
				Vui lòng <a href="${pageContext.request.contextPath}/login">đăng
					nhập</a> để xem thông tin cá nhân.
			</p>
		</c:if>
	</main>
</client:_layoutClient>
