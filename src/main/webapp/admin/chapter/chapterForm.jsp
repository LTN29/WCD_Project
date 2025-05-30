<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<admin:_layoutAdmin>
  <style>
    /* Container & card */
    .form-container, .chapter-list {
      background-color: #2c2f48;
      padding: 2rem;
      border-radius: 1rem;
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.3);
      max-width: 900px;
      margin: 2rem auto;
      color: #ddd;
    }

    /* Form inputs */
    .form-control, .form-select, textarea {
      background-color: #1e1e2f;
      border: 1px solid #444;
      color: #fff;
      border-radius: 0.75rem;
      transition: border-color 0.3s, box-shadow 0.3s;
    }
    .form-control:focus, .form-select:focus, textarea:focus {
      border-color: #4fd1c5;
      box-shadow: 0 0 8px rgba(79, 209, 197, 0.5);
      outline: none;
    }

    label {
      font-weight: 600;
      color: #ccc;
    }

    /* Buttons */
    .btn-success {
      background-color: #28a745;
      border-color: #28a745;
      border-radius: 0.75rem;
      font-weight: 600;
    }
    .btn-success:hover {
      background-color: #218838;
      border-color: #1e7e34;
    }
    .btn-secondary {
      background-color: #6c757d;
      border-color: #6c757d;
      border-radius: 0.75rem;
      font-weight: 600;
    }
    .btn-secondary:hover {
      background-color: #5a6268;
      border-color: #545b62;
    }
    .btn-sm {
      border-radius: 0.5rem;
    }

    /* Title */
    h2.page-title {
      color: #fff;
      font-weight: 700;
      text-align: center;
      margin-bottom: 1.5rem;
    }

    /* Table styling */
    table {
      width: 100%;
      border-collapse: separate;
      border-spacing: 0 0.75rem;
    }
    th, td {
      padding: 0.75rem 1rem;
      text-align: left;
      vertical-align: middle;
      color: #eee;
    }
    thead th {
      background-color: #364070;
      border-radius: 1rem;
      color: #a0d8d8;
      font-weight: 700;
    }
    tbody tr {
      background-color: #1e1e2f;
      border-radius: 1rem;
      transition: background-color 0.3s;
    }
    tbody tr:hover {
      background-color: #32406f;
    }

    /* Action buttons in table */
    .table .btn-primary {
      background-color: #3a6ef5;
      border-color: #3a6ef5;
    }
    .table .btn-primary:hover {
      background-color: #2c4ecc;
      border-color: #2c4ecc;
    }
    .table .btn-danger {
      background-color: #e55353;
      border-color: #e55353;
    }
    .table .btn-danger:hover {
      background-color: #b84040;
      border-color: #b84040;
    }
  </style>

  <div class="form-container">
    <h2 class="page-title">
      <c:choose>
        <c:when test="${chapter.id == null}">
          <i class="bi bi-pencil-square"></i> Thêm chương mới
        </c:when>
        <c:otherwise>
          <i class="bi bi-pencil-square"></i> Sửa chương #${chapter.id}
        </c:otherwise>
      </c:choose>
    </h2>

    <c:if test="${not empty errors}">
      <div class="alert alert-danger rounded-3" style="color:#fff; background-color:#b32828; border:none;">
        <span class="emoji">⚠️</span> Có lỗi xảy ra:
        <ul class="mb-0">
          <c:forEach var="err" items="${errors}">
            <li>${err}</li>
          </c:forEach>
        </ul>
      </div>
    </c:if>

    <form action="chapter" method="post" novalidate>
      <input type="hidden" name="action" value="${chapter.id == null ? 'add' : 'update'}" />
      <c:if test="${chapter.id != null}">
        <input type="hidden" name="id" value="${chapter.id}" />
      </c:if>

      <div class="mb-3">
        <label for="title" class="form-label">Tiêu đề chương</label>
        <input
          type="text"
          id="title"
          name="title"
          class="form-control"
          placeholder="Nhập tiêu đề chương..."
          value="${chapter.title != null ? chapter.title : ''}"
          required
          minlength="4"
          maxlength="200"
          aria-label="Tiêu đề chương"
        />
      </div>

      <div class="mb-3">
        <label for="dayCreate" class="form-label">Ngày tạo</label>
        <input
          type="date"
          id="dayCreate"
          name="dayCreate"
          class="form-control"
          value="${chapter.dayCreate != null ? chapter.dayCreate : ''}"
          required
          aria-label="Ngày tạo chương"
        />
      </div>

      <div class="mb-3">
        <label for="content" class="form-label">Nội dung chương</label>
        <textarea
          id="content"
          name="content"
          rows="8"
          class="form-control"
          placeholder="Nhập nội dung chương..."
          required
          aria-label="Nội dung chương"
        >${chapter.content != null ? chapter.content : ''}</textarea>
      </div>

      <div class="mb-3">
        <label for="storyIdSelect" class="form-label">Chọn truyện</label>
        <select name="storyIdSelect" id="storyIdSelect" class="form-select" required>
          <option value="">-- Chọn truyện --</option>
          <c:forEach var="story" items="${stories}">
            <option value="${story.id}" ${chapter != null && chapter.storyId == story.id ? "selected" : ""}>
              ${story.title}
            </option>
          </c:forEach>
        </select>
      </div>

      <div class="d-flex justify-content-between mt-4">
        <button type="submit" class="btn btn-success">
          <i class="bi bi-save"></i> Lưu chương
        </button>
        <a href="chapter" class="btn btn-secondary">
          <i class="bi bi-arrow-left"></i> Quay lại danh sách
        </a>
      </div>
    </form>
  </div>

  <c:if test="${not empty chaptersOfSelectedStory}">
    <div class="chapter-list">
      <h4>Danh sách chương của truyện "<strong>
        <c:forEach var="story" items="${stories}">
          <c:if test="${story.id == chapter.storyId}">
            ${story.title}
          </c:if>
        </c:forEach>
      </strong>"</h4>

      <table>
        <thead>
          <tr>
            <th>Mã chương</th>
            <th>Tiêu đề</th>
            <th>Ngày tạo</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="ch" items="${chaptersOfSelectedStory}">
            <tr>
              <td>${ch.id}</td>
              <td>${ch.title}</td>
              <td>${ch.dayCreate}</td>
              <td>
                <a href="chapter?action=edit&id=${ch.id}" class="btn btn-sm btn-primary">
                  <i class="bi bi-pencil"></i> Sửa
                </a>
                <a href="chapter?action=delete&id=${ch.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa chương này không?');">
                  <i class="bi bi-trash"></i> Xóa
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>

  <script>
    // Giới hạn ngày tạo không vượt quá ngày hiện tại
    const dayCreateInput = document.getElementById("dayCreate");
    if(dayCreateInput) {
      const today = new Date().toISOString().split('T')[0];
      dayCreateInput.setAttribute('max', today);
    }
  </script>
</admin:_layoutAdmin>
