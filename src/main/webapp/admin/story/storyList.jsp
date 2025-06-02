<<<<<<< HEAD
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
    <h2><i class="bi bi-person-lines-fill"></i>Danh sách truyện</h2>

    <!-- Tìm kiếm -->
    <form method="get" action="story" class="search-form mb-3">
      <input type="text" name="keyword" placeholder="🔍 Tìm theo tên truyen..." 
             value="${keyword != null ? keyword : ''}" />
      <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm kiếm</button>
      <a href="story" class="btn btn-outline-secondary"><i class="bi bi-x-circle"></i> Xóa</a>
    </form>
  
  <!-- Nút thêm mới -->
  
    <a href="story?action=add" class="btn btn-success fw-semibold px-4 py-2" style="width: fit-content;">
      <i class="bi bi-plus-circle me-1"></i> Thêm Truyện mới
    </a>
  

  <!-- Bảng truyện -->
  <div class="table-responsive mt-3">
    <table class="table table-bordered table-hover align-middle story-table text-nowrap">
      <thead>
        <tr>
          <th>ID</th>
          <th>Tiêu đề</th>
          <th>Chương</th>
          <th>Tác giả</th>
          <th>Thể loại</th>
          <th>Trạng thái</th>
          <th>Loại truyện</th>
          <th>Lịch phát hành</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="s" items="${stories}">
          <tr>
            <td>${s.id}</td>
            <td class="text-start px-3 fw-semibold" style="max-width: 200px;">
              <span class="d-inline-block text-truncate" style="max-width: 100%;">${s.title}</span>
            </td>
            <td>${s.chapterNumber}</td>
            <td>${s.authorName}</td>
            <td>${s.categoryName}</td>
            <td>
              <c:choose>
                <c:when test="${s.statusTitle == 'Hoàn tất'}">
                  <span class="badge bg-success">${s.statusTitle}</span>
                </c:when>
                <c:otherwise>
                  <span class="badge bg-warning">${s.statusTitle}</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <c:choose>
                <c:when test="${s.storyTypeTitle == 'Truyện tranh'}">
                  <span class="badge bg-primary">${s.storyTypeTitle}</span>
                </c:when>
                <c:otherwise>
                  <span class="badge bg-secondary">${s.storyTypeTitle}</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td>${s.scheduleDay}</td>
            <td class="action-buttons">
              <a href="story?action=edit&id=${c.id}" class="btn btn-warning btn-sm">
                <i class="bi bi-pencil-square"></i> Sửa
              </a>
              <a href="story?action=delete&id=${c.id}" class="btn btn-danger btn-sm"
                 onclick="return confirm('Xóa story này?')">
                <i class="bi bi-trash"></i> Xóa
              </a>
            </td>
            
          </tr>
        </c:forEach>
        <c:if test="${empty stories}">
          <tr>
            <td colspan="9" class="text-center text-muted">Không có truyện nào được tìm thấy.</td>
          </tr>
        </c:if>
      </tbody>
    </table>
  </div>
=======
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/admin/css/story/storyList.css" />
<admin:_layoutAdmin>
	<div class="container mt-4">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h3>Danh sách truyện</h3>
			<a href="story?action=add" class="btn btn-success">+ Thêm truyện</a>
		</div>

		<table class="story-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Tiêu đề</th>
					<th>Chương</th>
					<th>Tác giả</th>
					<th>Thể loại</th>
					<th>Trạng thái</th>
					<th>Loại</th>
					<th>Lịch phát hành</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${stories}">
					<tr>
						<td>${s.id}</td>
						<td>${s.title}</td>
						<td>${s.chapterNumber}</td>
						<td>${s.authorName}</td>
						<td>${s.categoryName}</td>
						<td>${s.statusTitle}</td>
						<td>${s.storyTypeTitle}</td>
						<td>${s.scheduleDay}</td>
						<td>
							<div class="table-actions">
								<a href="story?action=edit&id=${s.id}"
									class="btn btn-primary btn-sm">Sửa</a>
								<form method="post" action="story" style="display: inline;">
									<input type="hidden" name="action" value="delete" /> <input
										type="hidden" name="id" value="${s.id}" />
									<button type="submit" class="btn btn-danger btn-sm"
										onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</button>
								</form>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
>>>>>>> ea6c6e3d76476ac087f3641b8dcfa409ef395a94
</admin:_layoutAdmin>


