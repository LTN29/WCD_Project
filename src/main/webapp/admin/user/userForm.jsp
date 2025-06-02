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
      width: 100%;
      box-sizing: border-box;
    }

    .form-control:focus {
      border-color: #007bff;
      box-shadow: 0 0 0 0.2rem rgba(0,123,255,0.25);
      outline: none;
    }

    /* Trạng thái readonly/disabled */
    .form-control[readonly], .form-control[disabled] {
      background-color: #e9ecef;
      cursor: not-allowed;
      color: #6c757d;
    }

    .btn {
      border-radius: 8px;
      padding: 0.5rem 1rem;
      font-size: 0.95rem;
      font-weight: 500;
      cursor: pointer;
      border: none;
      display: inline-flex;
      align-items: center;
      gap: 0.3rem;
    }

    .btn-success {
      background-color: #28a745;
      color: #fff;
    }

    .btn-success:hover {
      background-color: #218838;
    }

    .btn-secondary {
      background-color: #6c757d;
      color: #fff;
      text-decoration: none;
      padding: 0.5rem 1rem;
      border-radius: 8px;
      display: inline-flex;
      align-items: center;
      gap: 0.3rem;
    }

    .btn-secondary:hover {
      background-color: #5a6268;
      color: #fff;
      text-decoration: none;
    }

    h3 {
      text-align: center;
      margin-bottom: 1.5rem;
      color: #007bff;
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
