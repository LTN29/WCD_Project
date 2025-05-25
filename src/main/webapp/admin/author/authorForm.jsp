<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Form Tác Giả</title>
</head>
<body>

<h2>${author.id > 0 ? 'Chỉnh sửa tác giả' : 'Thêm tác giả mới'}</h2>

<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

<form action="author" method="post">
    <input type="hidden" name="id" value="${author.id}" />

    <label>Tên tác giả:</label>
    <input type="text" name="name" value="${author.name}" /><br/>

    <label>Thông tin:</label>
    <textarea name="information">${author.information}</textarea><br/>

    <label>Ảnh:</label>
    <input type="text" name="image" value="${author.image}" /><br/>

    <button type="submit">Lưu</button>
</form>

</body>
</html>
