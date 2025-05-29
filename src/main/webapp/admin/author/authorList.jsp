<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    .btn-danger {
      background-color: #dc3545;
      border-color: #dc3545;
    }
    .btn-danger:hover {
      background-color: #c82333;
      border-color: #bd2130;
    }
    .btn-primary {
      background-color: #3c6ef5;
      border-color: #3c6ef5;
    }
    .btn-primary:hover {
      background-color: #315de0;
      border-color: #2f58cc;
    }
    .author-img {
      max-width: 80px;
      border-radius: 0.5rem;
    }
  </style>

  <div class="container">
    <h1><i class="bi bi-person-lines-fill"></i> Danh sách tác giả</h1>

    <!-- Form tìm kiếm và thêm mới -->
    <form method="get" action="author" class="search-form mb-3">
      <input type="text" name="keyword" value="${fn:escapeXml(param.keyword)}" class="form-control" placeholder="🔍 Tìm theo tên tác giả...">
      <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm</button>
      <a href="author?action=add" class="btn btn-success"><i class="bi bi-plus-circle"></i> Thêm tác giả</a>
    </form>

    <!-- Bảng danh sách tác giả -->
    <div class="table-container">
      <table class="table table-hover table-bordered align-middle">
        <thead class="table-primary text-center">
          <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Giới thiệu</th>
            <th>Ảnh</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="a" items="${authors}">
            <tr>
              <td class="text-center">${a.id}</td>
              <td>${a.name}</td>
              <td class="text-start">
                <c:choose>
                  <c:when test="${fn:length(a.information) > 100}">
                    ${fn:substring(a.information, 0, 97)}...
                  </c:when>
                  <c:otherwise>
                    ${a.information}
                  </c:otherwise>
                </c:choose>
              </td>
              <td class="text-center">
                <c:if test="${not empty a.image}">
                  <img src="${pageContext.request.contextPath}/admin/img/imgAuthor/${a.image}" alt="${a.name}" class="author-img" />
                </c:if>
              </td>
              <td class="text-center">
                <a href="author?action=edit&id=${a.id}" class="btn btn-sm btn-primary">
                  <i class="bi bi-pencil"></i> Sửa
                </a>
                <a href="author?action=delete&id=${a.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xoá tác giả này?');">
                  <i class="bi bi-trash"></i> Xóa
                </a>
              </td>
            </tr>
          </c:forEach>

          <c:if test="${empty authors}">
            <tr>
              <td colspan="5" class="text-center text-muted">Không có tác giả nào được tìm thấy.</td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</admin:_layoutAdmin>
