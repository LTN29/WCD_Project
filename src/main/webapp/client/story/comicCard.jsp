<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="comic-card">
    <img src="${pageContext.request.contextPath}/client/img/imgStory/${param.image != null ? param.image : 'default.png'}" alt="${param.title}" class="comic-cover" />
    <div class="comic-title">
        <a href="storyDetail?id=${param.id}">${param.title}</a>
    </div>
    <div class="comic-meta">
        <span>Tác giả: <a href="authorDetail?id=${param.authorId}">${param.authorName}</a></span>
        <span>Thể loại: ${param.categoryName}</span>
        <span>Chương: ${param.chapterNumber}</span>
        <span>Trạng thái: ${param.statusTitle}</span>
    </div>
    <div class="comic-stats">
        <span>👁 ${param.viewNumber}</span>
        <span>❤ ${param.likeNumber}</span>
        <span>★ ${param.followNumber}</span>
    </div>
    <div class="comic-intro">
        <c:choose>
            <c:when test="${fn:length(param.introduction) > 80}">
                ${fn:substring(param.introduction, 0, 80)}...
            </c:when>
            <c:otherwise>
                ${param.introduction}
            </c:otherwise>
        </c:choose>
    </div>
</div>

