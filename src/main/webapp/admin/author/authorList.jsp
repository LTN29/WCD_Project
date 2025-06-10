<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/admin/author/authorCSS/authorList.css" />
<admin:_layoutAdmin>

	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
		rel="stylesheet" />

	<div class="container mt-4">
		<h2>
			<i class="bi bi-person-lines-fill"></i> Danh sách Tác giả
		</h2>

		<!-- Tìm kiếm -->
		<form method="get" action="author" class="search-form mb-3">
			<input type="text" name="keyword"
				placeholder="🔍 Tìm theo tên tác giả..."
				value="${keyword != null ? keyword : ''}" />
			<button type="submit" class="btn btn-outline-primary">
				<i class="bi bi-search"></i> Tìm kiếm
			</button>
			<a href="author" class="btn btn-outline-secondary"><i
				class="bi bi-x-circle"></i> Xóa</a>
		</form>

		<!-- Thêm mới -->
		<a href="author?action=add" class="btn btn-success mb-3"> <i
			class="bi bi-plus-circle"></i> Thêm Tác giả mới
		</a>

		<!-- Bảng -->
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th style="width: 10%;">STT</th>
					<th style="width: 70%;">Tác giả</th>
					<th style="width: 20%;">Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="author" items="${authors}" varStatus="status">
					<tr>
						<td class="text-center">${status.index + 1}</td>
						<td>
							<div class="author-info">
								<c:choose>
									<c:when test="${not empty author.image}">
										<img
											src="${pageContext.request.contextPath}/admin/img/imgAuthor/${author.image}"
											class="avatar-img" alt="avatar">
									</c:when>
									<c:otherwise>
										<img
											src="${pageContext.request.contextPath}/admin/img/imgAuthor/default.png"
											class="avatar-img" alt="default-avatar">
									</c:otherwise>
								</c:choose>
								<span class="fw-semibold">${author.name}</span>
							</div>
						</td>
						<td class="action-buttons"><a
							href="author?action=edit&id=${author.id}"
							class="btn btn-warning btn-sm"> <i
								class="bi bi-pencil-square"></i> Sửa
						</a> <a href="author?action=delete&id=${author.id}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('Xóa tác giả này?')"> <i
								class="bi bi-trash"></i> Xóa
						</a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty authors}">
					<tr>
						<td colspan="3" class="text-center text-muted">Không có tác
							giả nào được tìm thấy.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</admin:_layoutAdmin>
