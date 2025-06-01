<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
  <style>
    .container {
      padding: 2rem;
      background-color: #f8fafc;
      border-radius: 12px;
      box-shadow: 0 4px 10px rgba(0,0,0,0.05);
    }

    h2 {
      font-size: 1.8rem;
      color: #1e293b;
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
    <h2><i class="bi bi-tags"></i> Danh sách Category</h2>

    <!-- Tìm kiếm -->
    <form method="get" action="category" class="search-form mb-3">
      <input type="text" name="keyword" placeholder="🔍 Tìm theo tên..." 
             value="${keyword != null ? keyword : ''}" />
      <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm kiếm</button>
      <a href="category" class="btn btn-outline-secondary"><i class="bi bi-x-circle"></i> Xóa</a>
    </form>

    <!-- Thêm mới -->
   <a href="category?action=add" class="btn btn-success mb-3">
  <i class="bi bi-plus-circle"></i> Thêm Category mới
</a>

    <!-- Bảng -->
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th style="width: 10%;">ID</th>
          <th style="width: 40%;">Tên</th>
          <th style="width: 20%;">Trạng thái</th>
          <th style="width: 30%;">Hành động</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="c" items="${categoryList}">
          <tr>
            <td class="text-center">${c.id}</td>
            <td>${c.name}</td>
            <td class="text-center">
              <c:choose>
                <c:when test="${c.active}">
                  <span class="status-active">Hoạt động</span>
                </c:when>
                <c:otherwise>
                  <span class="status-inactive">Không hoạt động</span>
                </c:otherwise>
              </c:choose>
            </td>
             <td class="action-buttons">
              <a href="category?action=edit&id=${c.id}" class="btn btn-warning btn-sm">
                <i class="bi bi-pencil-square"></i> Sửa
              </a>
              <a href="category?action=delete&id=${c.id}" class="btn btn-danger btn-sm"
                 onclick="return confirm('Xóa category này?')">
                <i class="bi bi-trash"></i> Xóa
              </a>
            </td>
            
          </tr>
        </c:forEach>

        <c:if test="${empty categoryList}">
          <tr>
            <td colspan="4" class="text-center text-muted">Không có category nào được tìm thấy.</td>
          </tr>
        </c:if>
      </tbody>
    </table>
  </div>
</admin:_layoutAdmin>
