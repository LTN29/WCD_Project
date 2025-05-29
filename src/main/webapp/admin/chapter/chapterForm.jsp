<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Bootstrap CSS & Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet" />

<style>
  :root {
    --bs-primary-rgb: 60, 110, 245;
  }
  .admin-card {
    border: none;
    border-radius: 1rem;
    box-shadow: 0 8px 18px rgba(0, 0, 0, 0.06);
  }
  .form-control, .form-select {
    border-radius: .75rem;
  }
  h2.page-title {
    font-weight: 600;
    margin-bottom: 1.5rem;
  }
  .alert {
    border: none;
    border-radius: .75rem;
    box-shadow: 0 2px 6px rgba(0,0,0,.05);
  }
  .btn .bi, .btn span.emoji {
    margin-right: .25rem;
  }
  .chapter-list {
    margin-top: 2rem;
  }
  .chapter-list table {
    width: 100%;
  }
</style>

<script>
  document.addEventListener("DOMContentLoaded", () => {
    const storySelect = document.getElementById("storySelect");
    const storyInput = document.getElementById("storyInput");
    const dayCreateInput = document.getElementById("dayCreate");

    function toggleStoryInput() {
      if (storySelect.value === "new") {
        storyInput.classList.remove("d-none");
        storyInput.required = true;
      } else {
        storyInput.classList.add("d-none");
        storyInput.required = false;
        storyInput.value = "";
      }
    }

    storySelect.addEventListener("change", toggleStoryInput);
    toggleStoryInput();

    // Giới hạn ngày tạo không vượt quá ngày hiện tại
    const today = new Date().toISOString().split('T')[0];
    dayCreateInput.setAttribute('max', today);
  });
</script>

<admin:_layoutAdmin>
  <div class="container py-4">
    <h2 class="page-title text-primary">
      <c:choose>
        <c:when test="${chapter.id == null}">
          <i class="bi bi-pencil-square"></i> Thêm chương
        </c:when>
        <c:otherwise>
          <i class="bi bi-pencil-square"></i> Sửa chương #${chapter.id}
        </c:otherwise>
      </c:choose>
    </h2>

    <c:if test="${not empty errors}">
      <div class="alert alert-danger">
        <span class="emoji">⚠️</span> Có lỗi xảy ra:
        <ul class="mb-0">
          <c:forEach var="err" items="${errors}">
            <li>${err}</li>
          </c:forEach>
        </ul>
      </div>
    </c:if>

    <div class="card admin-card p-4">
      <form action="chapter" method="post" class="row g-3" novalidate>
        <input type="hidden" name="action" value="${chapter.id == null ? 'add' : 'update'}" />
        <c:if test="${chapter.id != null}">
          <input type="hidden" name="id" value="${chapter.id}" />
        </c:if>

        <div class="col-md-6">
          <label for="title" class="form-label fw-semibold">Tiêu đề</label>
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

        <div class="col-md-6">
          <label for="dayCreate" class="form-label fw-semibold">Ngày tạo</label>
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

        <div class="col-12">
          <label for="content" class="form-label fw-semibold">Nội dung</label>
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

        <div class="col-md-6">
          <label for="storyIdSelect">Chọn truyện:</label>
<select name="storyIdSelect" id="storyIdSelect" class="form-control" required>
    <option value="">-- Chọn truyện --</option>
    <c:forEach var="story" items="${stories}">
        <option value="${story.id}" ${chapter != null && chapter.storyId == story.id ? "selected" : ""}>${story.title}</option>
    </c:forEach>
</select>


          <input
            type="text"
            id="storyInput"
            name="storyIdInput"
            class="form-control d-none"
            placeholder="Nhập mã truyện mới nếu không chọn trên"
            aria-label="Nhập mã truyện mới"
            value="${param.storyIdInput != null ? param.storyIdInput : ''}"
          />
        </div>

        <div class="col-12 d-flex gap-2 mt-3">
          <button type="submit" class="btn btn-success">
            <span class="emoji">💾</span> Lưu
          </button>
          <a href="chapter" class="btn btn-secondary">
            <span class="emoji">⬅️</span> Quay lại
          </a>
        </div>
      </form>
    </div>

    <c:if test="${not empty chaptersOfSelectedStory}">
      <div class="chapter-list card admin-card p-4">
        <h4>
          Danh sách chương của truyện "<strong>
            <c:forEach var="story" items="${stories}">
              <c:if test="${story.id == chapter.storyId}">
                ${story.title}
              </c:if>
            </c:forEach>
          </strong>"
        </h4>

        <table class="table table-striped table-bordered mt-3">
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
  </div>
</admin:_layoutAdmin>
