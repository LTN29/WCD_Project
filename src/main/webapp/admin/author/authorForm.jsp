<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
  <style>
    .form-container {
      background-color: #2c2f48;
      padding: 2rem;
      border-radius: 1rem;
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.3);
      max-width: 800px;
      margin: auto;
    }

    .form-group label {
      color: #ddd;
      font-weight: 500;
    }

    .form-control {
      background-color: #1e1e2f;
      border: 1px solid #444;
      color: #fff;
    }

    .form-control:focus {
      border-color: #4fd1c5;
      box-shadow: 0 0 0 0.2rem rgba(79, 209, 197, 0.25);
    }

    .btn-success {
      background-color: #28a745;
      border-color: #28a745;
    }

    .btn-secondary {
      background-color: #6c757d;
      border-color: #6c757d;
    }

    .btn-success:hover {
      background-color: #218838;
    }

    .btn-secondary:hover {
      background-color: #5a6268;
    }

    h3 {
      text-align: center;
      margin-bottom: 1.5rem;
      color: #fff;
    }

    small.text-muted {
      color: #bbb !important;
    }
  </style>

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
          <label>Tên tác giả</label>
          <input class="form-control" name="name" value="${author.name}" required>
        </div>

        <div class="form-group mb-3">
          <label>Thông tin</label>
          <textarea class="form-control" name="information" rows="5">${author.information}</textarea>
        </div>

        <div class="form-group mb-4">
          <label>Ảnh đại diện</label>
          <input class="form-control" name="image" value="${author.image}">
          <small class="form-text text-muted">Nhập đường dẫn đến ảnh đại diện</small>
        </div>

        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-success"><i class="bi bi-save"></i> Lưu</button>
          <a href="author" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Quay lại</a>
        </div>
      </form>
    </div>
  </div>
</admin:_layoutAdmin>
