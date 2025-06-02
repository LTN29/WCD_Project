<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

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
    <h2><i class="bi bi-person-lines-fill"></i>Quản lý Chương</h2>

    <!-- Tìm kiếm -->
    <form method="get" action="chapter" class="search-form mb-3">
      <input type="text" name="keyword" placeholder="🔍 Tìm theo tên Chương..." 
             value="${keyword != null ? keyword : ''}" />
      <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm kiếm</button>
      <a href="chapter" class="btn btn-outline-secondary"><i class="bi bi-x-circle"></i> Xóa</a>
    </form>
  
  <!-- Nút thêm mới -->
  
    <a href="chapter?action=add" class="btn btn-success fw-semibold px-4 py-2" style="width: fit-content;">
      <i class="bi bi-plus-circle me-1"></i> Thêm Chương mới
    </a>
    

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th><th>Tiêu đề</th><th>Ngày tạo</th><th>Story ID</th><th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${chapterList}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.title}</td>
                    <td>${c.dayCreate}</td>
                    <td>${c.storyId}</td>
                    <td>
                        <a href="chapter?action=edit&id=${c.id}" class="btn btn-warning btn-sm">✏️ Sửa</a>
                        <a href="chapter?action=delete&id=${c.id}" class="btn btn-danger btn-sm" onclick="return confirm('Xoá chương này?')">🗑️ Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</admin:_layoutAdmin>
