<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/admin/author/authorCSS/authorForm.css" />
<admin:_layoutAdmin>

  <div class="container mt-4">
    <div class="form-container">
      <h3><i class="bi bi-person-lines-fill"></i> ${author != null ? "Chỉnh sửa tác giả" : "Thêm tác giả mới"}</h3>

      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>

      <form method="post" action="author" autocomplete="off">
        <input type="hidden" name="action" value="${author != null ? 'update' : 'add'}">

        <c:if test="${author != null}">
          <input type="hidden" name="id" value="${author.id}">
        </c:if>

        <div class="form-group mb-3">
          <label for="name">Tên tác giả</label>
          <input id="name" class="form-control" name="name" value="${author.name}" required>
        </div>

        <div class="form-group mb-3">
          <label for="information">Thông tin</label>
          <textarea id="information" class="form-control" name="information" rows="5">${author.information}</textarea>
        </div>

        <div class="form-group mb-4">
          <label for="image">Ảnh đại diện</label>
          <input id="image" class="form-control" name="image" value="${author.image}" ${author == null ? 'required' : ''}>
          <small class="form-text text-muted">Nhập đường dẫn đến ảnh đại diện</small>
        </div>

        <c:if test="${author != null && author.image != null && not empty author.image}">
          <div class="text-center">
            <img src="${author.image}" alt="Ảnh đại diện" class="author-preview">
          </div>
        </c:if>

        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-success"><i class="bi bi-save"></i> Lưu</button>
          <a href="author" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Quay lại</a>
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
