<%@ tag language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/layoutClient.css" />

<header class="client-header">
    <div class="client-header__container">
        <div class="client-header__logo">
            <a href="${pageContext.request.contextPath}/Home">
                <img src="${pageContext.request.contextPath}/client/img/logo.jpg" alt="Logo" />
            </a>
        </div>
        <div class="client-header__nav">
            <a href="#">DISCORD</a>
            <a href="#">HỎI ĐÁP</a>
            <a href="#">BẢNG TRUYỆN</a>
            <a href="#">TIN TỨC</a>
        </div>
        <!-- Dropdown Category -->
        <div class="header-category-dropdown">
            <button class="header-dropbtn">
                Thể loại <span>&#x25BC;</span>
            </button>
            <div class="header-dropdown-content">
                <c:forEach var="cat" items="${categories}">
                    <a href="${pageContext.request.contextPath}/categoryStoryList?categoryId=${cat.id}">${cat.name}</a>
                </c:forEach>
            </div>
        </div>
        <div class="client-header__tools">
            <input class="client-header__search" placeholder="Tìm kiếm..." />
            <button class="client-header__icon">
                <img src="${pageContext.request.contextPath}/client/img/User_Icon.png" alt="User" />
            </button>
        </div>
    </div>
</header>

<main class="client-main">
    <jsp:doBody />
</main>

<footer class="client-footer">
    <div class="client-footer__container">
        <div class="client-footer__contact">
            <b>Contact for work, copyright and more:</b>
            <br/>
            <a href="mailto:ad.cuutruyen@gmail.com">ad.cuutruyen@gmail.com</a>
        </div>
        <div class="client-footer__policy">
            <a href="#">Điều khoản dịch vụ</a> | <a href="#">Chính sách bảo mật</a>
        </div>
        <div class="client-footer__copyright">
            © 2023 - cuutruyen.net
        </div>
    </div>
</footer>
