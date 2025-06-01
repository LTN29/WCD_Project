<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/client/css/storyList.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function() {
    $('#searchBtn').on('click', function(e) {
        e.preventDefault();
        filterStories();
    });
});
function filterStories() {
    $.get('story', {
        keyword : $('#keyword').val(),
        categoryId : $('#categoryId').val(),
        type : $('#type').val(),
        ajax : '1'
    }, function(res) {
        $('#storyGrid').html(res);
    });
}

</script>



<client:_layoutClient>
	<div class="story-list-container">
		<h2>
			<i class="fa fa-book"></i> BẢNG TRUYỆN
		</h2>
		<form id="searchForm"
			class="story-search-form d-flex align-items-center mb-4"
			style="gap: 8px;" onsubmit="return false;">
			<input type="text" id="keyword" name="keyword"
				placeholder="Tìm truyện theo tên..." value="${param.keyword}"
				class="form-control" style="width: 220px;"> <select
				id="categoryId" name="categoryId" class="form-select"
				style="width: 140px;">
				<option value="">-- Thể loại --</option>
				<c:forEach var="cat" items="${categories}">
					<option value="${cat.id}"
						<c:if test="${param.categoryId == cat.id}">selected</c:if>>${cat.name}</option>
				</c:forEach>
			</select> <select id="type" name="type" class="form-select"
				style="width: 140px;">
				<option value="">Tất cả</option>
				<option value="1" <c:if test="${param.type == '1'}">selected</c:if>>Truyện
					tranh</option>
				<option value="2" <c:if test="${param.type == '2'}">selected</c:if>>Truyện
					chữ</option>
			</select>
			<button type="button" id="searchBtn" class="btn btn-primary">Tìm kiếm</button>
		</form>

		<div id="storyGrid">
			<jsp:include page="storyGrid.jsp" />
		</div>

		<div class="pagination">
			<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> ...
		</div>
		<div class="comment-block mt-4">
			<h5>
				<i class="fa fa-comments"></i> Bình luận về bảng truyện
			</h5>
			<div class="comment-list">
				<c:choose>
					<c:when test="${not empty commentList}">
						<c:forEach var="comment" items="${commentList}">
							<div class="comment-item">
								<b>${comment.userName}:</b> ${comment.content}
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="comment-empty">Chưa có bình luận nào.</div>
					</c:otherwise>
				</c:choose>
			</div>
			<form class="comment-input-row" action="CommentServlet" method="post">
				<input type="hidden" name="storyList" value="1" /> <input
					type="text" name="content"
					placeholder="Bình luận về bảng truyện..." required />
				<button type="submit">Gửi</button>
			</form>
		</div>


	</div>
</client:_layoutClient>
