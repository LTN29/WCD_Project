<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
    <h2>${chapter.id == 0 ? "Thêm Chương Mới" : "Chỉnh sửa Chương"}</h2>
    <form method="post" action="../AdminChapterServlet">
        <input type="hidden" name="id" value="${chapter.id}" />
        <div class="mb-3">
            <label>Tiêu đề:</label>
            <input type="text" name="title" value="${chapter.title}" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Nội dung:</label>
            <textarea name="content" rows="5" class="form-control" required>${chapter.content}</textarea>
        </div>
        <div class="mb-3">
            <label>Ngày tạo:</label>
            <input type="date" name="dayCreate" value="${chapter.dayCreate}" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Story ID:</label>
            <input type="number" name="storyId" value="${chapter.storyId}" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">💾 Lưu</button>
        <a href="../AdminChapterServlet" class="btn btn-secondary">🔙 Quay lại</a>
</form>
</admin:_layoutAdmin>
