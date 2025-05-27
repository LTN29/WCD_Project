<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<admin:_layoutAdmin>
<div class="container py-4">
    <h2 class="text-primary mb-4">📖 Danh sách chương</h2>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="mb-3">
        <a href="chapter?action=add" class="btn btn-primary">➕ Thêm chương mới</a>
    </div>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Ngày tạo</th>
                <th>Mã truyện</th>
                <th class="text-center">Thao tác</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${chapters}">
            <tr>
                <td>${c.id}</td>
                <td>${c.title}</td>
                <td>${c.dayCreate}</td>
                <td>${c.storyId}</td>
                <td class="text-center">
                    <a class="btn btn-sm btn-info" href="chapter?action=detail&id=${c.id}">👁️ Xem</a>
                    <a class="btn btn-sm btn-warning" href="chapter?action=edit&id=${c.id}">✏️ Sửa</a>
                    <a class="btn btn-sm btn-danger" href="chapter?action=delete&id=${c.id}"
                       onclick="return confirm('Bạn có chắc chắn muốn xoá chương #${c.id}?');">🗑️ Xoá</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty chapters}">
            <tr><td colspan="5" class="text-center text-muted">Không có chương nào.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</admin:_layoutAdmin>
