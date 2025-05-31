<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
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
				<p>
					<span title="Like"><i class="fa fa-heart"></i>
						${story.likeNumber}</span> <span title="Follow"><i
						class="fa fa-star"></i> ${story.followNumber}</span> <span title="View"><i
						class="fa fa-eye"></i> ${story.viewNumber}</span>
				</p>
			</div>

		</div>
		<hr>
		<h4>Chapters</h4>
		<ul class="list-group">
			<c:forEach var="c" items="${chapters}">
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					<a href="chapterRead?id=${c.id}">${c.title}</a> <span
					class="badge badge-info">${c.dayCreate}</span>
				</li>
			</c:forEach>
		</ul>
	</div>
	<style>
.detail-container {
	margin-top: 32px;
}
</style>
</client:_layoutClient>
