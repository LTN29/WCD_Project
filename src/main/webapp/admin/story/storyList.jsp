<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<admin:_layoutAdmin>
  <style>
    .search-form {
      display: flex;
      gap: 1rem;
      margin-bottom: 1.5rem;
    }
    .search-form input[type="text"] {
      flex: 1;
    }
    .table-container {
      background: white;
      border-radius: 1rem;
      padding: 2rem;
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.06);
    }
    table th, table td {
      vertical-align: middle;
    }
    .btn-sm .bi {
      margin-right: 0.25rem;
    }
  </style>

  <div class="container mt-4">
    <h1><i class="bi bi-book"></i> Danh sách truyện</h1>

    <!-- Tìm kiếm và thêm mới -->
    <form method="get" action="story" class="search-form">
      <input type="text" name="keyword" value="${fn:escapeXml(param.keyword)}" class="form-control" placeholder="🔍 Tìm theo tiêu đề truyện...">
      <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm</button>
      <a href="story?action=add" class="btn btn-success"><i class="bi bi-plus-circle"></i> Thêm truyện</a>
    </form>

    <div class="table-container">
      <table class="table table-hover table-bordered align-middle">
        <thead class="table-primary text-center">
          <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Số chương</th>
            <th>Tác giả</th>
            <th>Thể loại</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="s" items="${stories}">
            <tr>
              <td class="text-center">${s.id}</td>
              <td>${s.title}</td>
              <td class="text-center">${s.chapterNumber}</td>
              <td>${s.authorName}</td>
              <td>${s.categoryName}</td>
              <td>${s.statusTitle}</td>
              <td class="text-center">
                <a href="story?action=edit&id=${s.id}" class="btn btn-sm btn-primary"><i class="bi bi-pencil"></i> Sửa</a>
                <form method="post" action="story" style="display:inline;">
                  <input type="hidden" name="action" value="delete">
                  <input type="hidden" name="id" value="${s.id}">
                  <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa truyện này?')">
                    <i class="bi bi-trash"></i> Xóa
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${empty stories}">
            <tr>
              <td colspan="7" class="text-center text-muted">Không có truyện nào được tìm thấy.</td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</admin:_layoutAdmin>
