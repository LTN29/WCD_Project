<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Quản trị hệ thống</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/layoutadmin.css">
</head>

<body>
  <nav class="admin-navbar">
    <div class="admin-container">
      <a class="admin-logo" href="${pageContext.request.contextPath}/admin">Admin Panel</a>
      <ul class="admin-nav">
        <li><a href="${pageContext.request.contextPath}/admin/story/storyList.jsp">Truyện</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/author/authorList.jsp">Tác giả</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/user">Người dùng</a></li>
      </ul>
      <ul class="admin-user-nav">
        <c:choose>
          <c:when test="${not empty sessionScope.user}">
            <li><span>Xin chào, ${sessionScope.user.name}</span></li>
            <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </nav>

  <main class="admin-main">
    <jsp:doBody />
  </main>

  <footer class="admin-footer">
    &copy; 2024 - Hệ thống quản trị
  </footer>
</body>
</html>
