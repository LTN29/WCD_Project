<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<client:_layoutClient>
    <div class="chapter-container">
        <h2 class="chapter-title">${chapter.title}</h2>
        
        <div class="chapter-images">
            <c:forEach var="img" items="${images}">
                <img src="${pageContext.request.contextPath}/chapter_images/${img}" alt="Page" class="chapter-image" loading="lazy"/>
            </c:forEach>
        </div>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/chapters?storyId=${chapter.storyId}" class="btn btn-secondary">← Quay lại danh sách chương</a>
        </div>
    </div>

</client:_layoutClient>
