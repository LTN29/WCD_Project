<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<admin:_layoutAdmin>
    <div class="container mt-4">
        <h3>${author != null ? "Chỉnh sửa tác giả" : "Thêm tác giả mới"}</h3>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <form method="post" action="author" autocomplete="off">
            <input type="hidden" name="action" value="${author != null ? 'update' : 'add'}">
            
            <c:if test="${author != null}">
                <input type="hidden" name="id" value="${author.id}">
            </c:if>
            
            <div class="form-group">
                <label>Tên tác giả</label>
                <input class="form-control" name="name" value="${author.name}" required>
            </div>
            
            <div class="form-group">
                <label>Thông tin</label>
                <textarea class="form-control" name="information" rows="5">${author.information}</textarea>
            </div>
            
            <div class="form-group">
                <label>Ảnh đại diện</label>
                <input class="form-control" name="image" value="${author.image}">
                <small class="form-text text-muted">Nhập đường dẫn đến ảnh</small>
            </div>
            
            <button type="submit" class="btn btn-success">Lưu</button>
            <a href="author" class="btn btn-secondary">Quay lại</a>
        </form>
    </div>
</admin:_layoutAdmin>