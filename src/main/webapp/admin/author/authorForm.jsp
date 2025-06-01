<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
  <style>
    body {
      background-color: #f4f6f9;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .form-container {
      background-color: #ffffff;
      padding: 2rem 2.5rem;
      border-radius: 16px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
      max-width: 720px;
      margin: auto;
    }

    .form-group label {
      font-weight: 600;
      color: #333;
      margin-bottom: 0.5rem;
      display: block;
    }

    .form-control {
      background-color: #f9f9f9;
      border: 1px solid #ccc;
      color: #333;
      border-radius: 8px;
      padding: 0.5rem 0.75rem;
      transition: border-color 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
    }

    .form-control:focus {
      border-color: #007bff;
      box-shadow: 0 0 0 0.2rem rgba(0,123,255,0.25);
      outline: none;
    }

    .btn {
      border-radius: 8px;
      padding: 0.5rem 1rem;
      font-size: 0.95rem;
      font-weight: 500;
    }

    .btn-success {
      background-color: #28a745;
      border-color: #28a745;
      color: #fff;
    }

    .btn-success:hover {
      background-color: #218838;
      border-color: #1e7e34;
    }

    .btn-secondary {
      background-color: #6c757d;
      border-color: #6c757d;
      color: #fff;
    }

    .btn-secondary:hover {
      background-color: #5a6268;
      border-color: #545b62;
    }

    h3 {
      text-align: center;
      margin-bottom: 1.5rem;
      color: #007bff;
    }

    small.text-muted {
      color: #6c757d !important;
      font-size: 0.875rem;
    }

    .alert-danger {
      background-color: #f8d7da;
      border-color: #f5c6cb;
      color: #721c24;
      border-radius: 8px;
      padding: 0.75rem 1rem;
      margin-bottom: 1rem;
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
          <label for="name">Tên tác giả</label>
          <input id="name" class="form-control" name="name" value="${author.name}" required>
        </div>

        <div class="form-group mb-3">
          <label for="information">Thông tin</label>
          <textarea id="information" class="form-control" name="information" rows="5">${author.information}</textarea>
        </div>

        <div class="form-group mb-4">
          <label for="image">Ảnh đại diện</label>
          <input id="image" class="form-control" name="image" value="${author.image}">
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
