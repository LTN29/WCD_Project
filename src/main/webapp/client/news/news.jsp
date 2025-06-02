<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/news.css" />
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
<client:_layoutClient>
<div class="news-wrapper">
    <h2>Tin tức mới nhất</h2>
    <div class="news-list">
        <c:forEach var="news" items="${newsList}">
            <div class="news-card">
                <img src="${news.image}" alt="${news.title}" class="news-thumb"/>
                <div class="news-content">
                    <h3>${news.title}</h3>
                    <div class="news-meta">${news.date}</div>
                    <p>${news.summary}</p>
                    <a href="newsDetail?id=${news.id}" class="read-more">Đọc tiếp</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</client:_layoutClient>