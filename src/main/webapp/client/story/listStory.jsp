<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>
<client:_layoutClient>
    <h3>${listTitle}</h3>
<div class="comics-list">
    <c:forEach var="s" items="${stories}">
        <jsp:include page="comicCard.jsp">
            <jsp:param name="title" value="${s.title}" />
            <jsp:param name="image" value="${s.image}" />
            <jsp:param name="id" value="${s.id}" />
            <jsp:param name="authorId" value="${s.authorId}"/>
            <jsp:param name="authorName" value="${s.authorName}"/>
            <jsp:param name="categoryName" value="${s.categoryName}"/>
            <jsp:param name="chapterNumber" value="${s.chapterNumber}"/>
            <jsp:param name="statusTitle" value="${s.statusTitle}"/>
            <jsp:param name="viewNumber" value="${s.viewNumber}"/>
            <jsp:param name="likeNumber" value="${s.likeNumber}"/>
            <jsp:param name="followNumber" value="${s.followNumber}"/>
            <jsp:param name="introduction" value="${s.introduction}"/>
        </jsp:include>
    </c:forEach>
</div>

</client:_layoutClient>

