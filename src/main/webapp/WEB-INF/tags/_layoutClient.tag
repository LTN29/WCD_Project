<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/client/css/layoutClient.css" />

<header class="client-header">
	<div class="client-header__container">
		<div class="client-header__logo">
			<a href="${pageContext.request.contextPath}/Home"> <img
				src="${pageContext.request.contextPath}/client/img/logo.jpg"
				alt="Logo" />
			</a>
		</div>

		<div class="client-header__nav">
			<a href="${pageContext.request.contextPath}/discord">DISCORD</a> <a
				href="${pageContext.request.contextPath}/faq">HỎI ĐÁP</a> <a
				href="${pageContext.request.contextPath}/story">BẢNG TRUYỆN</a> <a
				href="${pageContext.request.contextPath}/news">TIN TỨC</a>
		</div>


		<div class="header-category-dropdown">
			<button class="header-dropbtn" type="button">
				Thể loại <span>&#x25BC;</span>
			</button>
			<div class="header-dropdown-content">
				<c:if test="${not empty categories}">
					<c:forEach var="cat" items="${categories}">
						<a
							href="${pageContext.request.contextPath}/story?categoryId=${cat.id}">
							${cat.name} </a>
					</c:forEach>
				</c:if>
				<c:if test="${empty categories}">
					<span style="padding: 10px 16px; display: block; color: gray;">Không
						có thể loại</span>
				</c:if>
			</div>

		</div>

		<div class="client-header__tools">
			<input class="client-header__search" placeholder="Tìm kiếm..." />
			<button class="client-header__icon">
				<img
					src="${pageContext.request.contextPath}/client/img/User_Icon.png"
					alt="User" />
			</button>
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<span style="margin-right: 10px; font-weight: bold;"> Xin
						chào, <a
						href="${pageContext.request.contextPath}/auth/user_info.jsp">${sessionScope.user.name}</a>
					</span>
					<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
				</c:otherwise>
			</c:choose>


		</div>
	</div>
</header>

<main class="client-main">
	<jsp:doBody />
</main>

<footer class="client-footer">
	<div class="client-footer__container">
		<div class="client-footer__contact">
			<b>Contact for work, copyright and more:</b><br /> <a
				href="mailto:ad.cuutruyen@gmail.com">ad.cuutruyen@gmail.com</a>
		</div>
		<div class="client-footer__policy">
			<a href="#">Điều khoản dịch vụ</a> | <a href="#">Chính sách bảo
				mật</a>
		</div>
		<div class="client-footer__copyright">© 2023 - cuutruyen.net</div>
	</div>
</footer>
