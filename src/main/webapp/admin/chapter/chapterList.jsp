<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
    <h2 class="mb-3">Quản lý Chương</h2>
   <a href="chapter?action=add" class="btn btn-success mb-3">➕ Thêm chương mới</a>

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th><th>Tiêu đề</th><th>Ngày tạo</th><th>Story ID</th><th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${chapterList}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.title}</td>
                    <td>${c.dayCreate}</td>
                    <td>${c.storyId}</td>
                    <td>
                        <a href="chapter?action=edit&id=${c.id}" class="btn btn-warning btn-sm">✏️ Sửa</a>
                        <a href="chapter?action=delete&id=${c.id}" class="btn btn-danger btn-sm" onclick="return confirm('Xoá chương này?')">🗑️ Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</admin:_layoutAdmin>
