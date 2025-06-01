<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>

<client:_layoutClient>
<div class="container detail-container">
    <div class="row">
        <div class="col-md-4 text-center">
            <img src="${pageContext.request.contextPath}/client/img/authors/${author.image}" 
                 alt="${author.name}" 
                 class="img-fluid rounded-circle shadow" 
                 style="max-width: 200px;">
        </div>
        <div class="col-md-8">
            <h2>${author.name}</h2>
            <p><strong>Bio:</strong> ${author.bio}</p>
            <p><strong>Total Stories:</strong> ${author.totalStories}</p>
            <p><strong>Followers:</strong> ${author.followerCount}</p>
        </div>
    </div>

    <hr>

    <h4>Stories by ${author.name}</h4>
    <ul class="list-group">
        <c:forEach var="story" items="${authorStories}">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <a href="storyDetails?id=${story.id}">
                    <strong>${story.title}</strong> 
                    <span class="text-muted small">(${story.chapterNumber} chapters)</span>
                </a>
                <span class="badge badge-secondary">${story.statusTitle}</span>
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
