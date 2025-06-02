<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Quản trị hệ thống</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/layoutAdmin.css">

</head>

<body>
<<<<<<< HEAD
  <div class="sidebar">
    <div class="logo">Admin Panel</div>
    <a href="${pageContext.request.contextPath}/admin">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/story">Truyện</a>
    <a href="${pageContext.request.contextPath}/admin/author">Tác giả</a>
    <a href="${pageContext.request.contextPath}/admin/category">Thể loại</a>
    <a href="${pageContext.request.contextPath}/admin/user">Người dùng</a>
    <a href="${pageContext.request.contextPath}/admin/chapter">Chapter</a>
    <a href="${pageContext.request.contextPath}/admin/settings">Cài đặt</a>
    <hr style="border-color: #444">
    <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <span style="padding: 12px 20px; display: block;">👤 ${sessionScope.user.name}</span>
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
      </c:otherwise>
    </c:choose>
  </div>
=======
	<div class="sidebar">
		<div class="logo">Admin Panel</div>
		<a href="${pageContext.request.contextPath}/Admin">Dashboard</a> <a
			href="${pageContext.request.contextPath}/Home">Client</a> <a
			href="${pageContext.request.contextPath}/admin/story">Truyện</a> <a
			href="${pageContext.request.contextPath}/admin/author">Tác giả</a> <a
			href="${pageContext.request.contextPath}/admin/category">Thể loại</a>
		<a href="${pageContext.request.contextPath}/admin/user">Người dùng</a>
		<a href="${pageContext.request.contextPath}/admin/report">Báo cáo</a>
		<a href="${pageContext.request.contextPath}/admin/settings">Cài
			đặt</a>
		<hr style="border-color: #444">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<span style="padding: 12px 20px; display: block;">👤
					${sessionScope.user.name}</span>
				<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			</c:otherwise>
		</c:choose>
	</div>
>>>>>>> ea6c6e3d76476ac087f3641b8dcfa409ef395a94

	<main class="admin-main">
		<jsp:doBody />
	</main>

<<<<<<< HEAD

=======
	<footer class="admin-footer"> &copy; 2024 - Hệ thống quản trị
	</footer>
>>>>>>> ea6c6e3d76476ac087f3641b8dcfa409ef395a94
</body>
</html>
