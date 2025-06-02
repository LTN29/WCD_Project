<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<admin:_layoutAdmin>
    <div class="container mt-4">
        <h3>${story != null ? "Edit Story" : "Add New Story"}</h3>
        <form method="post" action="story" autocomplete="off">
            <input type="hidden" name="action"
                value="${story != null ? 'update' : 'add'}">
            <c:if test="${story != null}">
                <input type="hidden" name="id" value="${story.id}">
            </c:if>
            <div class="form-group">
                <label>Title</label>
                <input class="form-control" name="title" value="${story.title}" required>
            </div>
            <div class="form-group">
                <label>Chapter Number</label>
                <input class="form-control" name="chapterNumber" type="number" min="0"
                    value="${story.chapterNumber}" required>
            </div>
            <div class="form-group">
                <label>Introduction</label>
                <textarea class="form-control" name="introduction">${story.introduction}</textarea>
            </div>
            <div class="form-group">
                <label>Image Filename</label>
                <input class="form-control" name="image" value="${story.image}">
            </div>
            <div class="form-group">
                <label>Author</label>
                <select class="form-control" name="authorId" required>
                    <option value="">-- Select Author --</option>
                    <c:forEach var="a" items="${authors}">
                        <option value="${a.id}"
                            ${story != null && story.authorId == a.id ? 'selected' : ''}>${a.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Status</label>
                <select class="form-control" name="statusId" required>
                    <option value="">-- Select Status --</option>
                    <c:forEach var="st" items="${statusList}">
                        <option value="${st.id}"
                            ${story != null && story.statusId == st.id ? 'selected' : ''}>${st.title}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Category</label>
                <select class="form-control" name="categoryId" required>
                    <option value="">-- Select Category --</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.id}"
                            ${story != null && story.categoryId == cat.id ? 'selected' : ''}>${cat.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Luôn hiện trường Story Type và Schedule -->
            <div class="form-group">
                <label>Story Type</label>
                <select class="form-control" name="storyTypeId" required>
                    <option value="">-- Select Story Type --</option>
                    <c:forEach var="t" items="${storyTypes}">
                        <option value="${t.id}"
                            ${story != null && story.storyTypeId == t.id ? 'selected' : ''}>${t.title}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Schedule</label>
                <select class="form-control" name="scheduleId" required>
                    <option value="">-- Select Schedule --</option>
                    <c:forEach var="s" items="${schedules}">
                        <option value="${s.id}"
                            ${story != null && story.scheduleId == s.id ? 'selected' : ''}>${s.day}</option>
                    </c:forEach>
                </select>
            </div>

            <c:if test="${story != null}">
                <div class="form-group">
                    <label>Likes</label>
                    <input class="form-control" value="${story.likeNumber}" readonly>
                </div>
                <div class="form-group">
                    <label>Follows</label>
                    <input class="form-control" value="${story.followNumber}" readonly>
                </div>
                <div class="form-group">
                    <label>Views</label>
                    <input class="form-control" value="${story.viewNumber}" readonly>
                </div>
            </c:if>
            <button type="submit" class="btn btn-success">Save</button>
            <a href="story" class="btn btn-secondary">Back</a>
        </form>
    </div>
</admin:_layoutAdmin>
