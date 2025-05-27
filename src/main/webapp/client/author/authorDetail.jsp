<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags" %>

<client:_layoutClient>
    <!-- Nội dung chính -->
    <div class="container author-detail-wrapper">
        <div class="author-detail-card">
            <!-- Kiểm tra xem author có tồn tại không -->
            <c:choose>
                <c:when test="${empty author}">
                    <div class="alert alert-warning text-center">
                        <div class="alert-icon">
                            <i class="fas fa-exclamation-triangle"></i>
                        </div>
                        <h4>Không tìm thấy thông tin tác giả</h4>
                        <p>Tác giả bạn đang tìm kiếm không tồn tại hoặc đã bị xóa.</p>
                        <a href="${pageContext.request.contextPath}/authors" class="btn btn-primary">
                            <i class="fas fa-arrow-left"></i> Quay lại danh sách
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <!-- Header với ảnh và tên -->
                    <div class="author-header">
                        <div class="author-avatar">
                            <c:choose>
                                <c:when test="${not empty author.image}">
                                    <img src="${pageContext.request.contextPath}/client/img/imgAuthor/${author.image}"
                                         alt="<c:out value='${author.name}'/>" 
                                         class="author-image"
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
                    
                    <!-- Thông tin chi tiết -->
                    <div class="author-info-section">
                        <div class="info-grid">
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-id-badge"></i>
                                </div>
                                <div class="info-content">
                                    <span class="info-label">ID</span>
                                    <span class="info-value">${author.id}</span>
                                </div>
                            </div>
                            
                            <c:if test="${not empty author.email}">
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-envelope"></i>
                                    </div>
                                    <div class="info-content">
                                        <span class="info-label">Email</span>
                                        <span class="info-value">
                                            <a href="mailto:${author.email}" class="email-link">
                                                <c:out value="${author.email}"/>
                                            </a>
                                        </span>
                                    </div>
                                </div>
                            </c:if>
                            
                            <c:if test="${not empty author.phone}">
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-phone"></i>
                                    </div>
                                    <div class="info-content">
                                        <span class="info-label">Điện thoại</span>
                                        <span class="info-value"><c:out value="${author.phone}"/></span>
                                    </div>
                                </div>
                            </c:if>
                            
                            <c:if test="${not empty author.createdDate}">
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-calendar-alt"></i>
                                    </div>
                                    <div class="info-content">
                                        <span class="info-label">Ngày tạo</span>
                                        <span class="info-value">
                                            <fmt:formatDate value="${author.createdDate}" pattern="dd/MM/yyyy"/>
                                        </span>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        
                        <!-- Thông tin mô tả -->
                        <c:if test="${not empty author.information}">
                            <div class="author-description">
                                <h3><i class="fas fa-info-circle"></i> Thông tin chi tiết</h3>
                                <div class="description-content">
                                    ${author.information}
                                </div>
                            </div>
                        </c:if>
                    </div>
                    
                    <!-- Action buttons -->
                    <div class="action-buttons">
                        <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Quay lại
                        </a>
                        
                        <c:if test="${hasEditPermission}">
                            <a href="${pageContext.request.contextPath}/admin/authors/edit/${author.id}" 
                               class="btn btn-primary">
                                <i class="fas fa-edit"></i> Chỉnh sửa
                            </a>
                        </c:if>
                        
                        <a href="${pageContext.request.contextPath}/articles?authorId=${author.id}" 
                           class="btn btn-info">
                            <i class="fas fa-book"></i> Xem bài viết
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    <!-- Custom CSS -->
    <style>
        * {
            box-sizing: border-box;
        }
        
        .author-detail-wrapper {
            min-height: calc(100vh - 200px);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px 15px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }
        
        .author-detail-card {
            background: #ffffff;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            max-width: 800px;
            width: 100%;
            margin: 0 auto;
            animation: slideUp 0.6s ease-out;
        }
        
        @keyframes slideUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        
        .author-header {
            background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%);
            color: white;
            text-align: center;
            padding: 50px 30px;
            position: relative;
            overflow: hidden;
        }
        
        .author-header::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: repeating-linear-gradient(
                45deg,
                transparent,
                transparent 2px,
                rgba(255,255,255,0.03) 2px,
                rgba(255,255,255,0.03) 4px
            );
            animation: rotate 20s linear infinite;
        }
        
        @keyframes rotate {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
        
        .author-avatar {
            position: relative;
            z-index: 2;
            margin-bottom: 20px;
        }
        
        .author-image {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 5px solid rgba(255, 255, 255, 0.2);
            transition: all 0.3s ease;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }
        
        .author-image:hover {
            transform: scale(1.05);
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.3);
        }
        
        .no-image {
            width: 150px;
            height: 150px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: rgba(255, 255, 255, 0.7);
            font-size: 48px;
            margin: 0 auto;
            border: 5px solid rgba(255, 255, 255, 0.2);
        }
        
        .author-title {
            position: relative;
            z-index: 2;
        }
        
        .author-title h1 {
            font-size: 2.5rem;
            font-weight: 700;
            margin: 0 0 10px 0;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }
        
        .author-subtitle {
            font-size: 1.1rem;
            opacity: 0.8;
            margin: 0;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-weight: 300;
        }
        
        .author-info-section {
            padding: 50px 40px;
        }
        
        .info-grid {
            display: grid;
            gap: 25px;
            margin-bottom: 40px;
        }
        
        .info-item {
            display: flex;
            align-items: center;
            padding: 20px;
            background: #f8f9fc;
            border-radius: 15px;
            border-left: 5px solid #667eea;
            transition: all 0.3s ease;
            position: relative;
            overflow: hidden;
        }
        
        .info-item::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255,255,255,0.4), transparent);
            transition: left 0.5s ease;
        }
        
        .info-item:hover {
            transform: translateX(5px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.1);
        }
        
        .info-item:hover::before {
            left: 100%;
        }
        
        .info-icon {
            width: 50px;
            height: 50px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 18px;
            margin-right: 20px;
            flex-shrink: 0;
        }
        
        .info-content {
            flex: 1;
        }
        
        .info-label {
            display: block;
            font-size: 0.9rem;
            color: #6c757d;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 5px;
        }
        
        .info-value {
            display: block;
            font-size: 1.1rem;
            color: #2c3e50;
            font-weight: 500;
        }
        
        .email-link {
            color: #667eea;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        
        .email-link:hover {
            color: #764ba2;
        }
        
        .author-description {
            background: linear-gradient(135deg, #f8f9fc 0%, #e3e7f3 100%);
            border-radius: 15px;
            padding: 30px;
            border: 1px solid #e1e5e9;
        }
        
        .author-description h3 {
            color: #2c3e50;
            margin-bottom: 20px;
            font-size: 1.3rem;
            font-weight: 600;
        }
        
        .author-description h3 i {
            color: #667eea;
            margin-right: 10px;
        }
        
        .description-content {
            color: #495057;
            line-height: 1.8;
            font-size: 1rem;
        }
        
        .action-buttons {
            padding: 30px 40px;
            background: #f8f9fc;
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }
        
        .btn {
            padding: 12px 25px;
            border-radius: 10px;
            text-decoration: none;
            font-weight: 600;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
            font-size: 0.95rem;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }
        
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }
        
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        
        .btn-primary {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
        }
        
        .btn-info {
            background: linear-gradient(135deg, #17a2b8, #138496);
            color: white;
        }
        
        .alert {
            background: #fff3cd;
            border: 1px solid #ffeaa7;
            border-radius: 15px;
            padding: 30px;
            text-align: center;
            margin: 40px;
        }
        
        .alert-icon {
            font-size: 3rem;
            color: #856404;
            margin-bottom: 20px;
        }
        
        .alert h4 {
            color: #856404;
            margin-bottom: 15px;
        }
        
        .alert p {
            color: #664d03;
            margin-bottom: 20px;
        }
        
        /* Responsive Design */
        @media (max-width: 768px) {
            .author-detail-wrapper {
                padding: 20px 10px;
            }
            
            .author-header {
                padding: 40px 20px;
            }
            
            .author-title h1 {
                font-size: 2rem;
            }
            
            .author-image, .no-image {
                width: 120px;
                height: 120px;
            }
            
            .author-info-section {
                padding: 30px 20px;
            }
            
            .info-item {
                padding: 15px;
            }
            
            .info-icon {
                width: 40px;
                height: 40px;
                margin-right: 15px;
                font-size: 16px;
            }
            
            .action-buttons {
                padding: 20px;
            }
            
            .btn {
                padding: 10px 20px;
                font-size: 0.9rem;
            }
        }
        
        @media (max-width: 480px) {
            .author-title h1 {
                font-size: 1.7rem;
            }
            
            .author-image, .no-image {
                width: 100px;
                height: 100px;
            }
            
            .info-item {
                flex-direction: column;
                text-align: center;
                gap: 15px;
            }
            
            .info-icon {
                margin: 0;
            }
            
            .action-buttons {
                flex-direction: column;
            }
            
            .btn {
                width: 100%;
                justify-content: center;
            }
        }
    </style>
</client:_layoutClient>