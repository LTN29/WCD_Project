<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<admin:_layoutAdmin>
    <h2>${category != null && category.id != 0 ? "Chỉnh sửa Category" : "Thêm Category mới"}</h2>
    <form method="post" action="category">
        <input type="hidden" name="id" value="${category != null ? category.id : 0}" />
        <div class="mb-3">
            <label>Tên:</label>
            <input type="text" name="name" value="${category != null ? category.name : ''}" class="form-control" required />
        </div>
        <div class="form-check mb-3">
            <input type="checkbox" class="form-check-input" id="active" name="active"
                   <c:if test="${category != null && category.active}">checked</c:if> />
            <label class="form-check-label" for="active">Hoạt động</label>
        </div>
        <button type="submit" class="btn btn-primary">💾 Lưu</button>
        <a href="category" class="btn btn-secondary">🔙 Quay lại</a>
    </form>
</admin:_layoutAdmin>
