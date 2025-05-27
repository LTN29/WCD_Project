<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<admin:_layoutAdmin>
<div class="container mt-4">
    <h3 class="mb-4">Chi tiết chương</h3>
    <div class="card">
        <div class="card-body">
            <p><strong>ID:</strong> ${chapter.id}</p>
            <p><strong>Tiêu đề:</strong> ${chapter.title}</p>
            <p><strong>Nội dung:</strong></p>
            <div class="border p-3 bg-light" style="white-space: pre-line">${chapter.content}</div>
            <p class="mt-3"><strong>Ngày tạo:</strong> ${chapter.dayCreate}</p>
            <p><strong>Mã truyện:</strong> ${chapter.storyId}</p>
        </div>
    </div>
    <a href="chapter" class="btn btn-secondary mt-3">⬅️ Quay lại danh sách</a>
</div>
</admin:_layoutAdmin>
