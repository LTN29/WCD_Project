<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<admin:_layoutAdmin>
<div class="container py-4">
    <h2 class="mb-4 text-primary">
        ${chapter.id == null ? "📝 Thêm chương mới" : "✏️ Chỉnh sửa chương"}
    </h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="chapter" method="post">
        <input type="hidden" name="action" value="${chapter.id == null ? 'add' : 'update'}">
        <c:if test="${chapter.id != null}">
            <input type="hidden" name="id" value="${chapter.id}">
        </c:if>

        <div class="mb-3">
            <label for="title" class="form-label">Tiêu đề</label>
            <input type="text" class="form-control" id="title" name="title" value="${chapter.title}" required>
        </div>

        <div class="mb-3">
            <label for="content" class="form-label">Nội dung</label>
            <textarea class="form-control" id="content" name="content" rows="8" required>${chapter.content}</textarea>
        </div>

        <div class="mb-3">
            <label for="dayCreate" class="form-label">Ngày tạo</label>
            <input type="date" class="form-control" id="dayCreate" name="dayCreate" value="${chapter.dayCreate}" required>
        </div>

        <div class="mb-3">
            <label for="storyId" class="form-label">Mã truyện</label>
            <input type="number" class="form-control" id="storyId" name="storyId" value="${chapter.storyId}" required>
        </div>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-success">💾 Lưu</button>
            <a href="chapter" class="btn btn-secondary">⬅️ Quay lại</a>
        </div>
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector("form");
        const dayCreateInput = document.getElementById("dayCreate");
        const today = new Date().toISOString().split('T')[0];
        if (!dayCreateInput.value) {
            dayCreateInput.value = today;
        }

        form.addEventListener("submit", function (e) {
            const dayCreateValue = new Date(dayCreateInput.value);
            const now = new Date();
            now.setHours(0, 0, 0, 0);

            if (dayCreateValue > now) {
                alert("❌ Ngày tạo không được vượt quá hôm nay.");
                e.preventDefault();
            }
        });
    });
</script>
</admin:_layoutAdmin>
