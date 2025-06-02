<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>
<client:_layoutClient>
<head>
    <title>Truyện bạn đã Follow</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/myFollow.css">
</head>
<body>
<div class="followed-container">
    <h2>Truyện bạn đã Follow</h2>
    <c:if test="${empty followedStories}">
        <div class="no-follow-msg">Bạn chưa theo dõi truyện nào.</div>
    </c:if>
    <div class="stories-grid">
        <c:forEach var="story" items="${followedStories}">
            <div class="story-card">
                <div class="story-img">
                    <img src="${pageContext.request.contextPath}/client/img/imgStory/${story.image != null ? story.image : 'default.png'}" alt="${story.title}">
                </div>
                <div class="story-info">
                    <a href="storyDetail?id=${story.id}" class="story-title">${story.title}</a>
                    <div><b>Tác giả:</b> ${story.authorName}</div>
                    <div><b>Thể loại:</b> ${story.categoryName}</div>
                    <div><b>Trạng thái:</b> ${story.statusTitle}</div>
                    <div><b>Kiểu truyện:</b> ${story.storyTypeTitle}</div>
                    <div><b>Lịch phát hành:</b> ${story.scheduleDay}</div>
                    <div><b>Chương:</b> ${story.chapterNumber} | <b>Lượt xem:</b> ${story.viewNumber}</div>
                    <form action="StoryUnfollow" method="post" class="unfollow-form">
                        <input type="hidden" name="storyId" value="${story.id}" />
                        <button type="submit" class="unfollow-btn">Bỏ Follow</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
</client:_layoutClient>