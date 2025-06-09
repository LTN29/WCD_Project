<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/chapter/chapterCSS/chapterList.css" />
<admin:_layoutAdmin>
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
                <th>STT</th><th>Tiêu đề</th><th>Ngày tạo</th><th>Truyện</th><th>Hành động</th>
            </tr>
        </thead>
        <tbody>
             <c:forEach var="c" items="${chapterList}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${c.title}</td>
                    <td>${c.dayCreate}</td>
                    <td>${c.storyTitle}</td>
                    <td>
                        <a href="chapter?action=edit&id=${c.id}" class="btn btn-warning btn-sm">✏️ Sửa</a>
                        <a href="chapter?action=delete&id=${c.id}" class="btn btn-danger btn-sm" onclick="return confirm('Xoá chương này?')">🗑️ Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
</admin:_layoutAdmin>
