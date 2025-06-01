<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
<client:_layoutClient>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/chapterDetail.css" />

    <div class="chapter-container">
        <h2 class="chapter-title">${chapter.title}</h2>
        
        <c:choose>
            <c:when test="${not empty images}">
                <div class="chapter-images">
                    <c:forEach var="img" items="${images}">
                        <img src="${pageContext.request.contextPath}/chapter_images/${img.image}" 
                             alt="Page" class="chapter-image" loading="lazy"/>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="chapter-content">
                    <c:out value="${chapter.content}" escapeXml="false" />
                </div>
            </c:otherwise>
        </c:choose>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/storyDetail?id=${chapter.storyId}" class="btn btn-secondary">
                ← Quay lại danh sách chương
            </a>
        </div>

        <!-- KHU VỰC COMMENT -->
        <div class="chapter-comments mt-4">
            <h4>
                <i class="fa fa-comments"></i> Bình luận (
                <c:out value="${fn:length(commentList)}" />
                )
            </h4>
            <div class="comment-list mb-2">
                <c:choose>
                    <c:when test="${not empty commentList}">
                        <c:forEach var="comment" items="${commentList}">
                            <div class="comment-item">
                                <span class="comment-author"><b>${comment.userName}:</b></span>
                                <span class="comment-content">${comment.content}</span>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="comment-empty">Chưa có bình luận nào.</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <form class="comment-input-row" action="CommentServlet" method="post">
                <input type="hidden" name="chapterId" value="${chapter.id}" />
                <input type="text" name="content" placeholder="Viết bình luận..." required />
                <button type="submit" class="btn btn-primary">Gửi</button>
            </form>
        </div>
    </div>
</client:_layoutClient>
