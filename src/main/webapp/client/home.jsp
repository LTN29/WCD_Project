<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>
<client:_layoutClient>
    <h2 class="section-title">MỚI CẬP NHẬT</h2>
    <div class="comics-list">
        <c:forEach var="s" items="${stories}">
            <div class="comic-card">
                <img src="${pageContext.request.contextPath}/client/img/${s.image}" alt="${s.title}" class="comic-cover" />
                <div class="comic-title">${s.title}</div>
                <div class="comic-chapter">Số chương: ${s.chapterNumber}</div>
                <div class="comic-intro">${s.introduction}</div>
            </div>
        </c:forEach>
    </div>
</client:_layoutClient>
