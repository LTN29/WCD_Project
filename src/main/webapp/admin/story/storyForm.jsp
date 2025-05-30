<<<<<<< HEAD
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<admin:_layoutAdmin>
  <style>
    .form-container {
      background-color: #2c2f48;
      padding: 2rem;
      border-radius: 1rem;
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.3);
      max-width: 800px;
      margin: auto;
    }

    .form-group label {
      color: #ddd;
      font-weight: 500;
    }

    .form-control {
      background-color: #1e1e2f;
      border: 1px solid #444;
      color: #fff;
    }

    .form-control:read-only {
      background-color: #343a50;
      color: #bbb;
    }

    .form-control:focus {
      border-color: #4fd1c5;
      box-shadow: 0 0 0 0.2rem rgba(79, 209, 197, 0.25);
    }

    .btn-success {
      background-color: #28a745;
      border-color: #28a745;
    }

    .btn-secondary {
      background-color: #6c757d;
      border-color: #6c757d;
    }

    h3 {
      text-align: center;
      margin-bottom: 1.5rem;
      color: #fff;
    }
  </style>

  <div class="container mt-4">
    <div class="form-container">
      <h3><i class="bi bi-pencil-square"></i> ${story != null ? "Edit Story" : "Add New Story"}</h3>
      <form method="post" action="story" autocomplete="off">
        <input type="hidden" name="action" value="${story != null ? 'update' : 'add'}">
        <c:if test="${story != null}">
          <input type="hidden" name="id" value="${story.id}">
        </c:if>

        <div class="form-group mb-3">
          <label>Title</label>
          <input class="form-control" name="title" value="${story.title}" required>
        </div>

        <div class="form-group mb-3">
          <label>Chapter Number</label>
          <input class="form-control" name="chapterNumber" type="number" min="0" value="${story.chapterNumber}" required>
        </div>

        <div class="form-group mb-3">
          <label>Introduction</label>
          <textarea class="form-control" name="introduction" rows="4">${story.introduction}</textarea>
        </div>

        <div class="form-group mb-3">
          <label>Image Filename</label>
          <input class="form-control" name="image" value="${story.image}">
        </div>

        <div class="form-group mb-3">
          <label>Author</label>
          <select class="form-control" name="authorId" required>
            <option value="">-- Select Author --</option>
            <c:forEach var="a" items="${authors}">
              <option value="${a.id}" ${story != null && story.authorId == a.id ? 'selected' : ''}>${a.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="form-group mb-3">
          <label>Status</label>
          <select class="form-control" name="statusId" required>
            <option value="">-- Select Status --</option>
            <c:forEach var="st" items="${statusList}">
              <option value="${st.id}" ${story != null && story.statusId == st.id ? 'selected' : ''}>${st.title}</option>
            </c:forEach>
          </select>
        </div>

        <div class="form-group mb-4">
          <label>Category</label>
          <select class="form-control" name="categoryId" required>
            <option value="">-- Select Category --</option>
            <c:forEach var="cat" items="${categories}">
              <option value="${cat.id}" ${story != null && story.categoryId == cat.id ? 'selected' : ''}>${cat.name}</option>
            </c:forEach>
          </select>
        </div>

        <c:if test="${story != null}">
          <div class="form-group mb-3">
            <label>Likes</label>
            <input class="form-control" value="${story.likeNumber}" readonly>
          </div>
          <div class="form-group mb-3">
            <label>Follows</label>
            <input class="form-control" value="${story.followNumber}" readonly>
          </div>
          <div class="form-group mb-3">
            <label>Views</label>
            <input class="form-control" value="${story.viewNumber}" readonly>
          </div>
        </c:if>

        <div class="d-flex justify-content-between mt-4">
          <button type="submit" class="btn btn-success"><i class="bi bi-save"></i> Save</button>
          <a href="story" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Back</a>
        </div>
      </form>
    </div>
  </div>
</admin:_layoutAdmin>
