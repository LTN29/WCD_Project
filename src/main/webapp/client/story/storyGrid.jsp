<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="story-list-grid">
    <c:choose>
        <c:when test="${not empty stories}">
            <c:forEach var="story" items="${stories}">
                <div class="story-card">
                    <img src="${pageContext.request.contextPath}/client/img/imgStory/${story.image}" alt="${story.title}">
                    <h5>${story.title}</h5>
                    <div>Thể loại: ${story.categoryName}</div>
                    <div>
                        <i class="fa fa-star text-warning"></i> ${story.followNumber}
                        | <i class="fa fa-heart text-danger"></i> ${story.likeNumber}
                    </div>
                    <div>
                        <i class="fa fa-eye text-primary"></i> ${story.viewNumber}
                    </div>
                    <a href="storyDetail?id=${story.id}" class="btn btn-outline-primary btn-sm mt-2">Xem chi tiết</a>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div>Không tìm thấy danh sách truyện!</div>
        </c:otherwise>
    </c:choose>
</div>
