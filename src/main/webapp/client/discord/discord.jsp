<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/discord.css" />
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
<client:_layoutClient>
<div class="discord-invite-wrapper">
    <div class="discord-card">
        <img class="discord-logo" src="https://cdn.jsdelivr.net/gh/edent/SuperTinyIcons/images/svg/discord.svg" alt="Discord">
        <h2>Tham gia cộng đồng Discord</h2>
        <p>Cập nhật tin tức, hỏi đáp, hỗ trợ và giao lưu cùng các thành viên khác!</p>
        <a href="https://discord.com/invite/yourinvitecode" target="_blank" class="discord-join-btn">Tham gia ngay</a>
    </div>
</div>
</client:_layoutClient>