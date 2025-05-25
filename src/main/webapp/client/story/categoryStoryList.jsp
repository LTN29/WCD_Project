<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>
<client:_layoutClient>
    <div class="container mt-4">
        <h3>Stories in category: ${categoryName}</h3>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Chapters</th>
                    <th>Author</th>
                    <th>Status</th>
                    <th>Likes</th>
                    <th>Follows</th>
                    <th>Views</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${stories}">
                    <tr>
                        <td>${s.id}</td>
                        <td><a href="storyDetail?id=${s.id}">${s.title}</a></td>
                        <td>${s.chapterNumber}</td>
                        <td>${s.authorName}</td>
                        <td>${s.statusName}</td>
                        <td>${s.likeNumber}</td>
                        <td>${s.followNumber}</td>
                        <td>${s.viewNumber}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/client/home.jsp" class="btn btn-secondary">Back to home</a>
    </div>
</client:_layoutClient>
