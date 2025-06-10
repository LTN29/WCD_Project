<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/admin/author/authorCSS/commentList.css" />
<admin:_layoutAdmin>
	<div class="container mt-4">
		<h3>Duyệt bình luận truyện</h3>
		<c:if test="${empty storyComments}">
			<p>Không có bình luận truyện đang chờ duyệt.</p>
		</c:if>
		<c:forEach var="comment" items="${storyComments}">
			<div class="border p-2 mb-2">
				<p>
					<b>${comment.userName}</b> bình luận: ${comment.content}
				</p>
				<!-- Bình luận truyện -->
				<form
					action="${pageContext.request.contextPath}/admin/comment/approve"
					method="post">
					<input type="hidden" name="id" value="${comment.id}" /> <input
						type="hidden" name="type" value="story" />
					<button class="btn btn-success btn-sm" name="action"
						value="approve">Duyệt</button>
					<button class="btn btn-danger btn-sm" name="action" value="reject">Từ
						chối</button>
				</form>
			</div>
		</c:forEach>

		<hr>

		<h3>Duyệt bình luận chương</h3>
		<c:if test="${empty chapterComments}">
			<p>Không có bình luận chương đang chờ duyệt.</p>
		</c:if>
		<c:forEach var="comment" items="${chapterComments}">
			<div class="border p-2 mb-2">
				<p>
					<b>${comment.userName}</b> bình luận: ${comment.content}
				</p>
			
				<form
					action="${pageContext.request.contextPath}/admin/comment/approve"
					method="post">
					<input type="hidden" name="id" value="${comment.id}" /> <input
						type="hidden" name="type" value="chapter" />
					<button class="btn btn-success btn-sm" name="action"
						value="approve">Duyệt</button>
					<button class="btn btn-danger btn-sm" name="action" value="reject">Từ
						chối</button>
				</form>

			</div>
		</c:forEach>
	</div>
</admin:_layoutAdmin>
