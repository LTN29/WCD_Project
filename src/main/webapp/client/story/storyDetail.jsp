<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<client:_layoutClient>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/client/css/storyDetail.css">
	<div class="container detail-container">
		<div class="row">
			<div class="col-md-4">
				<img
					src="${pageContext.request.contextPath}/client/img/imgStory/${story.image != null ? story.image : 'default.png'}"
					alt="${story.title}" class="img-fluid rounded shadow"
					style="max-width: 100%">
			</div>
			<div class="col-md-8">
				<h2>${story.title}</h2>
				<p>
					<strong>Author:</strong> ${story.authorName}
				</p>
				<p>
					<strong>Category:</strong> ${story.categoryName}
				</p>
				<p>
					<strong>Status:</strong> ${story.statusTitle}
				</p>
				<p>
					<strong>Type:</strong> ${story.storyTypeTitle}
				</p>
				<p>
					<strong>Schedule:</strong> ${story.scheduleDay}
				</p>
				<p>
					<strong>Chapters:</strong> ${story.chapterNumber}
				</p>
				<p>
					<strong>Introduction:</strong> ${story.introduction}
				</p>
				<div class="d-flex align-items-center gap-3 mb-3">
					<button class="btn btn-outline-danger d-flex align-items-center"
						id="likeBtn">
						<i class="fa fa-heart me-1"></i> <span id="likeCount">${story.likeNumber}</span>
						<span class="ms-1">Like</span>
					</button>
					<button class="btn btn-outline-primary d-flex align-items-center"
						id="followBtn">
						<i class="fa fa-star me-1"></i> <span id="followCount">${story.followNumber}</span>
						<span class="ms-1">Follow</span>
					</button>
					<div class="d-flex align-items-center">
						<i class="fa fa-eye text-primary me-1"></i> <span>${story.viewNumber}</span>
					</div>
					<div class="d-flex align-items-center">
						<i class="fa fa-comments text-info me-1"></i> <span
							id="commentCount"> <c:out
								value="${not empty commentList ? fn:length(commentList) : 0}" />
						</span>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<h4>
			<i class="fa fa-list"></i> Chapters
		</h4>
		<ul class="list-group mb-4">
			<c:forEach var="c" items="${chapters}">
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					<a href="ChapterList?chapterId=${c.id}"> <i
						class="fa fa-book-open"></i> ${c.title}
				</a> <span class="badge badge-info">${c.dayCreate}</span>
				</li>
			</c:forEach>
		</ul>

		<!-- Khu vực comment -->
		<div class="comment-block">
			<h5>
				<i class="fa fa-comments"></i> Comments (
				<c:out value="${fn:length(commentList)}" />
				)
			</h5>
			<div class="comment-list">
				<c:choose>
					<c:when test="${not empty commentList}">
						<c:forEach var="comment" items="${commentList}">
							<div>
								<span><b>${comment.userName}:</b> ${comment.content}</span>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="comment-empty">Chưa có bình luận nào.</div>
					</c:otherwise>
				</c:choose>
			</div>
			<form class="comment-input-row" action="CommentServlet" method="post">
				<input type="hidden" name="chapterId" value="${chapter.id}" /> <input
					type="text" name="content" placeholder="Viết bình luận..." required />
				<button type="submit">Gửi</button>
			</form>
		</div>
</client:_layoutClient>
