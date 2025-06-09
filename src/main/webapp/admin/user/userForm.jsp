<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/user/userCSS/userForm.css" />
<admin:_layoutAdmin>
  
  <div class="container mt-4">
    <div class="form-container">
      <h3><i class="bi bi-person-lines-fill"></i> ${user != null ? "Thông tin User" : "Thêm User mới"}</h3>

      <c:if test="${not empty error}">
        <div class="alert-danger">${error}</div>
      </c:if>

      <form method="post" action="user" autocomplete="off">
        <c:if test="${user != null}">
          <input type="hidden" name="id" value="${user.id}" />
        </c:if>

        <div class="form-group mb-3">
          <label for="username">Username</label>
          <input id="username" class="form-control" type="text" name="username" value="${user != null ? user.userName : ''}" readonly />
        </div>

        <div class="form-group mb-3">
          <label for="password">Password (đã mã hóa)</label>
          <!-- Hiển thị mật khẩu đã mã hóa dưới dạng disabled -->
          <input id="password" class="form-control" type="password" value="********" disabled />
          <small class="form-text text-muted">Mật khẩu không thể thay đổi tại đây.</small>
        </div>

        <div class="form-group mb-3">
          <label for="name">Tên đầy đủ</label>
          <input id="name" class="form-control" type="text" name="name" value="${user != null ? user.name : ''}" required />
        </div>

        <div class="form-group mb-3">
          <label for="image">Ảnh đại diện</label>
          <input id="image" class="form-control" type="text" name="image" value="${user != null ? user.image : ''}" />
          <small class="form-text text-muted">Nhập đường dẫn đến ảnh đại diện</small>
        </div>

        <div class="form-group mb-3">
          <label for="score">Điểm số</label>
          <input id="score" class="form-control" type="number" name="score" min="0" value="${user != null ? user.score : 0}" />
        </div>

        <div class="form-group mb-3">
          <label for="active">Active</label><br/>
          <input id="active" type="checkbox" name="active" ${user != null && user.active == 1 ? "checked" : ""} />
        </div>

        <div class="form-group mb-3">
          <label for="role">Role</label>
          <input id="role" class="form-control" type="text" name="role" value="${user != null ? user.role : ''}" readonly />
        </div>

        <div class="form-group mb-4">
          <label for="levelId">Level ID</label>
          <input id="levelId" class="form-control" type="number" name="levelId" value="${user != null ? user.levelId : 1}" readonly />
        </div>

        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-success"><i class="bi bi-save"></i> Lưu</button>
          <a href="user" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Quay lại</a>
        </div>
      </form>
    </div>
  </div>

  <script>
    window.onload = () => {
      document.getElementById('name').focus();
    };
  </script>
</admin:_layoutAdmin>
