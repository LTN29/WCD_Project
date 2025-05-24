<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<admin:_layoutAdmin>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3>Stories List</h3>
            <a href="story?action=add" class="btn btn-success">+ Add New Story</a>
        </div>
        <table class="table table-striped table-hover table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Chapters</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${stories}">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.title}</td>
                        <td>${s.chapterNumber}</td>
                        <td>${s.authorName}</td>
                        <td>${s.categoryName}</td>
                        <td>${s.statusTitle}</td>
                        <td>
                            <a href="story?action=edit&id=${s.id}" class="btn btn-primary btn-sm">Edit</a>
                            <form method="post" action="story" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${s.id}">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete this story?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</admin:_layoutAdmin>
