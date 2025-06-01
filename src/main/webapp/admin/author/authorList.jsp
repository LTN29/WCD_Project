<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/author/authorCSS/style.css" />

<style>
  .avatar-img {
    width: 60px;
    height: 60px;
    object-fit: cover;
    border-radius: 50%;
    border: 2px solid #e2e8f0;
    box-shadow: 0 2px 6px rgba(0,0,0,0.05);
  }
</style>


  <div class="container mt-4">
    <h1><i class="bi bi-person-lines-fill"></i> Danh sách Tác giả</h1>

    <!-- Tìm kiếm và thêm mới -->
    <form method="get" action="author" class="search-form">
      <input type="text" name="keyword" value="${fn:escapeXml(param.keyword)}" class="form-control" placeholder="🔍 Tìm theo tên tác giả...">
      <button type="submit" class="btn btn-outline-info"><i class="bi bi-search"></i> Tìm</button>
      <a href="author?action=add" class="btn btn-success"><i class="bi bi-plus-circle"></i> Thêm tác giả</a>
    </form>

    <!-- Bảng danh sách tác giả -->
    <div class="table-container">
      <table class="table table-hover table-bordered align-middle">
        <thead>
          <tr>
            <th>#</th>
            <th>Ảnh</th>
            <th>Tên tác giả</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="author" items="${authors}" varStatus="status">
            <tr>
              <td>${status.index + 1}</td>
              <td>
                <c:choose>
                  <c:when test="${not empty author.image}">
                    <img src="${pageContext.request.contextPath}/admin/img/imgAuthor/${author.image}" class="avatar-img" alt="avatar">
                  </c:when>
                  <c:otherwise>
                    <img src="${pageContext.request.contextPath}/admin/img/imgAuthor/default.png" class="avatar-img" alt="default-avatar">
                  </c:otherwise>
                </c:choose>
              </td>
              <td>${author.name}</td>
              <td>
                <a href="author?action=edit&id=${author.id}" class="btn btn-sm btn-primary">
                  <i class="bi bi-pencil"></i> Sửa
                </a>
                <form method="post" action="author" style="display:inline;">
                  <input type="hidden" name="action" value="delete">
                  <input type="hidden" name="id" value="${author.id}">
                  <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa tác giả này?')">
                    <i class="bi bi-trash"></i> Xóa
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${empty authors}">
            <tr>
              <td colspan="4" class="text-center text-muted">Không có tác giả nào được tìm thấy.</td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</admin:_layoutAdmin>
