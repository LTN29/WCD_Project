<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<admin:_layoutAdmin>
<div class="container mt-4">

    <!-- THÔNG BÁO -->
    <c:if test="${not empty success}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${success}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="mb-0">Danh sách tác giả</h3>
        <a href="author?action=add" class="btn btn-success">+ Thêm tác giả</a>
    </div>

    <!-- DANH SÁCH -->
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover align-middle">
            <thead class="table-dark text-center">
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
                        <td class="fw-bold">${a.name}</td>
                        <td>
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
                               <img src="${pageContext.request.contextPath}/admin/img/imgAuthor/${a.image}"
     alt="${a.name}" width="60" height="60"
     class="rounded shadow-sm" style="object-fit: cover;">


                            </c:if>
                        </td>
                        <td class="text-center">
                            <a href="author?action=edit&id=${a.id}" class="btn btn-sm btn-primary me-1">✏️ Sửa</a>
                            <a href="author?action=delete&id=${a.id}" class="btn btn-sm btn-danger"
                               onclick="return confirm('Bạn có chắc chắn muốn xoá tác giả này?');">🗑️ Xoá</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty authors}">
                    <tr>
                        <td colspan="5" class="text-center text-muted">Không có tác giả nào.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</admin:_layoutAdmin>
