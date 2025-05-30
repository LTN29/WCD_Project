<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>

<admin:_layoutAdmin>
  <style>
  <style>
    .search-form {
      display: flex;
      gap: 1rem;
      margin-bottom: 1.5rem;
      flex-wrap: wrap;
    }

    .search-form input[type="text"] {
      flex: 1;
      background-color: #1e1e2f;
      border: 1px solid #444;
      color: #fff;
    }

    .table-container {
      background: #2c2f48;
      border-radius: 1rem;
      padding: 2rem;
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.3);
    }

    .table {
      color: #fff;
    }

    /* Giống story: header màu nền đậm */
    .table th {
      background-color: #3a3f63;
      color: #fff;
      text-align: center;
    }

    /* Nội dung ô trong bảng */
    .table td {
      background-color: #2c2f48;
      color: #f0f0f0;
      vertical-align: middle;  /* Thêm cho căn giữa chiều dọc */
      text-align: center;      /* Căn giữa ngang giống story */
    }

    .table-hover tbody tr:hover {
      background-color: #383c59;
    }

    .btn-sm .bi {
      margin-right: 0.25rem;
    }

    .text-muted {
      color: #aaa !important;
    }
  </style>

  </style>

  <div class="container">
    <h1><i class="bi bi-journal-text"></i> Danh sách chương</h1>

    <form method="get" action="" class="search-form mb-3">
      <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="🔍 Tìm theo tiêu đề...">
      <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm</button>
      <a href="?action=add" class="btn btn-success"><i class="bi bi-plus-circle"></i> Thêm chương</a>
    </form>

    <div class="table-container">
      <table class="table table-hover table-bordered align-middle">
        <thead class="table-primary text-center">
          <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Ngày tạo</th>
            <th>Mã truyện</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="c" items="${chapters}">
            <tr>
              <td class="text-center">${c.id}</td>
              <td>${c.title}</td>
              <td class="text-center">${c.dayCreate}</td>
              <td class="text-center">${c.storyId}</td>
              <td class="text-center">
                <a href="?action=edit&id=${c.id}" class="btn btn-sm btn-primary">
                  <i class="bi bi-pencil"></i> Sửa
                </a>
                <form method="post" action="" style="display:inline;">
                  <input type="hidden" name="action" value="delete">
                  <input type="hidden" name="id" value="${c.id}">
                  <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa chương này không?');">
                    <i class="bi bi-trash"></i> Xóa
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${empty chapters}">
            <tr>
              <td colspan="5" class="text-center text-muted">Không có chương nào được tìm thấy.</td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</admin:_layoutAdmin>