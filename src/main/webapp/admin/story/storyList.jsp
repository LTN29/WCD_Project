<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
  <!-- Bootstrap Icons + custom CSS -->
  <link rel="stylesheet" href="/admin/author/authorCSS/style.css" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet" />

  <!-- ======= Thanh công cụ ======= -->
    <!-- ======= Tiêu đề trang ======= -->
  <h2 class="page-title mb-3">
    <i class="bi bi-journal-bookmark-fill me-2"></i>Danh sách truyện
  </h2>

  <!-- ======= Thanh công cụ ======= -->
  <form class="toolbar d-flex flex-wrap align-items-center gap-2" action="story" method="get">
      <input type="text" name="keyword" value="${keyword}"
             class="form-control shadow-sm flex-grow-1 search-input"
             placeholder="Tìm theo tiêu đề, tác giả, thể loại…" />

      <input type="hidden" name="action" value="search"/>

      <button class="btn btn-search d-flex align-items-center gap-1">
        <i class="bi bi-search"></i><span>Tìm kiếm</span>
      </button>

      <a href="story?action=add"
         class="btn btn-add d-flex align-items-center gap-1">
        <i class="bi bi-plus-circle"></i><span>Thêm truyện mới</span>
      </a>
  </form>

  <!-- ======= Bảng danh sách ======= -->
  <div class="table-responsive mt-3">
    <table class="table table-bordered table-hover align-middle shadow-sm rounded text-center story-table">
      <thead class="table-dark sticky-top">
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
            <td class="text-start px-3 fw-semibold">${s.title}</td>
            <td>${s.chapterNumber}</td>
            <td>${s.authorName}</td>
            <td>${s.categoryName}</td>
            <td>
              <span class="badge ${s.statusTitle == 'Hoàn tất' ? 'bg-success' : 'bg-warning'}">
                ${s.statusTitle}
              </span>
            </td>
            <td>
              <span class="badge ${s.storyTypeTitle == 'Truyện tranh' ? 'bg-primary' : 'bg-secondary'}">
                ${s.storyTypeTitle}
              </span>
            </td>
            <td>${s.scheduleDay}</td>
            <td>
              <div class="d-flex justify-content-center gap-2">
                <a href="story?action=edit&id=${s.id}" class="btn btn-edit btn-sm">
                  <i class="bi bi-pencil-square me-1"></i>Sửa
                </a>
                <form method="post" action="story" 
                      onsubmit="return confirm('Bạn có chắc muốn xóa truyện này?')">
                  <input type="hidden" name="action" value="delete" />
                  <input type="hidden" name="id" value="${s.id}" />
                  <button type="submit" class="btn btn-delete btn-sm">
                    <i class="bi bi-trash me-1"></i>Xoá
                  </button>
                </form>
              </div>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</admin:_layoutAdmin>