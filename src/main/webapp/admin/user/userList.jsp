<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet" />

  <style>
    .container {
      padding: 2rem;
      background-color: #f8fafc;
      border-radius: 12px;
      box-shadow: 0 4px 10px rgba(0,0,0,0.05);
    }

    h2 {
      font-size: 1.8rem;
      color: #7f3c3c;
      margin-bottom: 1.5rem;
      font-weight: 600;
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .search-form {
      display: flex;
      gap: 10px;
      margin-bottom: 20px;
      flex-wrap: wrap;
    }

    .search-form input[type="text"] {
      flex: 1;
      min-width: 250px;
      padding: 8px 12px;
      border-radius: 8px;
      border: 1px solid #cbd5e1;
    }

    .btn {
      border-radius: 8px !important;
      padding: 6px 12px !important;
      font-size: 14px !important;
    }

    .btn-sm {
      padding: 4px 10px !important;
      font-size: 13px !important;
    }

    .table {
      background-color: #ffffff;
      border-radius: 8px;
      overflow: hidden;
    }

    .table th {
      background-color: #e2e8f0;
      text-align: center;
      vertical-align: middle;
    }

    .table td {
      vertical-align: middle;
    }

    .status-active {
      color: #10b981;
      font-weight: bold;
    }

    .status-inactive {
      color: #ef4444;
      font-weight: bold;
    }

    .action-buttons {
      display: flex;
      gap: 8px;
      justify-content: center;
    }

    .mb-3 {
      margin-bottom: 1rem !important;
    }

    .mt-4 {
      margin-top: 1.5rem !important;
    }
  </style>

  <div class="container mt-4">
    <h2><i class="bi bi-person-lines-fill"></i> Danh sách tài khoản</h2>

    <!-- Tìm kiếm -->
    <form method="get" action="user" class="search-form mb-3">
      <input type="text" name="keyword" placeholder="🔍 Tìm theo tên tài khoản..." 
             value="${keyword != null ? keyword : ''}" />
      <button type="submit" class="btn btn-outline-primary">
        <i class="bi bi-search"></i> Tìm kiếm
      </button>
      <a href="user" class="btn btn-outline-secondary">
        <i class="bi bi-x-circle"></i> Xóa
      </a>
    </form>
  
    <!-- Nút thêm mới -->
    <a href="user?action=add" class="btn btn-success fw-semibold px-4 py-2" style="width: fit-content;">
      <i class="bi bi-plus-circle me-1"></i> Thêm tài khoản mới
    </a>

    <div class="table-responsive mt-3">
      <table class="table table-bordered table-hover align-middle story-table text-nowrap">
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Username</th>
            <th>Role</th>
            <th>Level</th>
            <th>Score</th>
            <th>Active</th>
            <th>Ảnh</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="user" items="${userList}">
            <tr>
              <td>${user.id}</td>
              <td class="text-start px-3 fw-semibold" style="max-width: 200px;">
                <span class="d-inline-block text-truncate" style="max-width: 100%;">${user.name}</span>
              </td>
              <td>${user.userName}</td>
              <td>${user.role}</td>
              <td>${user.level}</td>
              <td>${user.score}</td>
              <td>
                <c:choose>
                  <c:when test="${user.active == 1}">Active</c:when>
                  <c:otherwise>Inactive</c:otherwise>
                </c:choose>
              </td>
              <td>
                <img src="${user.image}" alt="avatar" width="50" />
              </td>
              <td class="action-buttons">
                <a href="user?action=edit&id=${user.id}" class="btn btn-warning btn-sm">
                  <i class="bi bi-pencil-square"></i> Sửa
                </a>
                <a href="user?action=delete&id=${user.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Xóa user này?')">
                  <i class="bi bi-trash"></i> Xóa
                </a>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty userList}">
            <tr>
              <td colspan="9" class="text-center text-muted">Không có tài khoản nào được tìm thấy.</td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</admin:_layoutAdmin>
