<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>

<client:_layoutClient>
    <style>
        .container.author-detail-wrapper {
            display: flex;
            justify-content: center;
            padding: 40px 15px;
            background-color: #f8f9fa;
            min-height: 100vh;
        }

        .author-detail-card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            padding: 30px;
            max-width: 700px;
            width: 100%;
            text-align: center;
        }

        .author-header {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 30px;
        }

        .author-avatar {
            width: 130px;
            height: 130px;
            border-radius: 50%;
            overflow: hidden;
            margin-bottom: 15px;
            background-color: #e9ecef;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .author-avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
        }

        .no-image i {
            font-size: 60px;
            color: #6c757d;
        }

        .author-title h1 {
            margin: 0;
            font-size: 28px;
            color: #343a40;
        }

        .author-subtitle {
            font-size: 16px;
            color: #6c757d;
        }

        .author-description {
            text-align: left;
            margin-top: 20px;
        }

        .author-description h3 {
            font-size: 18px;
            margin-bottom: 10px;
            color: #0d6efd;
        }

        .description-content {
            background: #f1f3f5;
            padding: 15px;
            border-radius: 8px;
            color: #212529;
        }

        .action-buttons {
            margin-top: 30px;
            display: flex;
            justify-content: center;
            gap: 10px;
            flex-wrap: wrap;
        }

        .alert {
            padding: 30px;
            border-radius: 12px;
        }

        .alert-icon i {
            font-size: 40px;
            color: #ffc107;
            margin-bottom: 10px;
        }

        .alert h4 {
            font-size: 22px;
        }

        .btn {
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 15px;
        }
    </style>

    <div class="container author-detail-wrapper">
        <div class="author-detail-card">
            <c:choose>
                <c:when test="${empty author}">
                    <div class="alert alert-warning text-center">
                        <div class="alert-icon">
                            <i class="fas fa-exclamation-triangle"></i>
                        </div>
                        <h4>Không tìm thấy thông tin tác giả</h4>
                        <p>Tác giả bạn đang tìm kiếm không tồn tại hoặc đã bị xóa.</p>
                        <a href="${pageContext.request.contextPath}/Home" class="btn btn-primary">
                            <i class="fas fa-arrow-left"></i> Về trang chủ
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="author-header">
                        <div class="author-avatar">
                            <c:choose>
                                <c:when test="${not empty author.image}">
                                    <img src="${pageContext.request.contextPath}/client/img/imgAuthor/${author.image}"
                                         alt="<c:out value='${author.name}'/>"
                                         onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';">
                                    <div class="no-image" style="display: none;">
                                        <i class="fas fa-user"></i>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="no-image">
                                        <i class="fas fa-user"></i>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="author-title">
                            <h1><c:out value="${author.name}" default="Không có tên"/></h1>
                            <p class="author-subtitle">Tác giả</p>
                        </div>
                    </div>

                    <c:if test="${not empty author.information}">
                        <div class="author-description">
                            <h3><i class="fas fa-info-circle"></i> Thông tin chi tiết</h3>
                            <div class="description-content">
                                ${author.information}
                            </div>
                        </div>
                    </c:if>

                    <div class="action-buttons">
                        <a href="${pageContext.request.contextPath}/Home" class="btn btn-primary">
                            <i class="fas fa-arrow-left"></i> Về trang chủ
                        </a>

                        <c:if test="${hasEditPermission}">
                            <a href="${pageContext.request.contextPath}/admin/authors/edit/${author.id}"
                               class="btn btn-secondary">
                                <i class="fas fa-edit"></i> Chỉnh sửa
                            </a>
                        </c:if>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</client:_layoutClient>
