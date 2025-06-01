<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="root.entities.Chapter" %>
<%
    List<Chapter> chapters = (List<Chapter>) request.getAttribute("chapters");
    int currentPage = (int) request.getAttribute("page");
    int totalPage = (int) request.getAttribute("totalPage");
    String keyword = (String) request.getAttribute("keyword");
%>
<html>
<head>
    <title>Quản lý Chương</title>
    <style>
        body { font-family: Arial; background: #f9f9f9; }
        .container { width: 90%; margin: auto; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        a.btn { padding: 6px 10px; background: #007BFF; color: white; border-radius: 5px; text-decoration: none; }
        a.btn:hover { background: #0056b3; }
        .search-bar { margin-top: 20px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Quản lý Chương</h1>

    <div class="search-bar">
        <form method="get">
            <input type="text" name="keyword" placeholder="Tìm theo tiêu đề..." value="<%= keyword != null ? keyword : "" %>" />
            <button type="submit">Tìm kiếm</button>
            <a class="btn" href="chapter?action=add">Thêm chương</a>
        </form>
    </div>

    <table>
        <tr>
            <th>ID</th><th>Tiêu đề</th><th>Ngày tạo</th><th>Truyện</th><th>Hành động</th>
        </tr>
        <%
            for (Chapter c : chapters) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getTitle() %></td>
            <td><%= c.getDayCreate() %></td>
            <td><%= c.getStoryId() %></td>
            <td>
                <a class="btn" href="chapter?action=edit&id=<%= c.getId() %>">Sửa</a>
                <a class="btn" style="background:red;" onclick="return confirm('Bạn chắc chắn xóa?')" href="chapter?action=delete&id=<%= c.getId() %>">Xóa</a>
            </td>
        </tr>
        <% } %>
    </table>

    <div style="margin-top: 20px;">
        Trang:
        <% for (int i = 1; i <= totalPage; i++) { %>
            <a href="?page=<%= i %>&keyword=<%= keyword != null ? keyword : "" %>"><%= (i == currentPage ? "<b>" + i + "</b>" : i) %></a>
        <% } %>
    </div>
</div>
</body>
</html>
