<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/admin/css/story/storyList.css" />
<admin:_layoutAdmin>
	<div class="container mt-4">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h3>Danh sách truyện</h3>
			<a href="story?action=add" class="btn btn-success">+ Thêm truyện</a>
		</div>
		<form method="get" action="story" class="search-form-story">
			<input type="hidden" name="action" value="list" /> <input
				type="text" name="keyword" class="form-control"
				placeholder="Tìm kiếm theo tiêu đề..."
				value="${keyword != null ? keyword : ''}" /> <select
				name="categoryId" class="form-select">
				<option value="">-- Thể loại --</option>
				<c:forEach var="c" items="${categories}">
					<option value="${c.id}" ${c.id == categoryId ? 'selected' : ''}>${c.name}</option>
				</c:forEach>
			</select> <select name="type" class="form-select">
				<option value="">-- Loại truyện --</option>
				<c:forEach var="t" items="${storyTypes}">
					<option value="${t.id}" ${t.id == type ? 'selected' : ''}>${t.title}</option>
				</c:forEach>
			</select>

			<button type="submit" class="btn btn-primary">Tìm kiếm</button>
		</form>

		<table class="story-table">
			<thead>
				<tr>
					<th>Tiêu đề</th>
					<th>Chương</th>
					<th>Tác giả</th>
					<th>Thể loại</th>
					<th>Trạng thái</th>
					<th>Loại</th>
					<th>Lịch phát hành</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${stories}">
					<tr>
						<td>${s.title}</td>
						<td>${s.chapterNumber}</td>
						<td>${s.authorName}</td>
						<td>${s.categoryName}</td>
						<td>${s.statusTitle}</td>
						<td>${s.storyTypeTitle}</td>
						<td>${s.scheduleDay}</td>
						<td>
							<div class="table-actions">
								<a href="story?action=edit&id=${s.id}"
									class="btn btn-primary btn-sm">Sửa</a>
								<form method="post" action="story" style="display: inline;">
									<input type="hidden" name="action" value="delete" /> <input
										type="hidden" name="id" value="${s.id}" />
									<button type="submit" class="btn btn-danger btn-sm"
										onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</button>
								</form>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</admin:_layoutAdmin>

