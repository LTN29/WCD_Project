<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="root.entities.Chapter" %>
<%
    Chapter c = (Chapter) request.getAttribute("chapter");
%>
<html>
<head>
    <title><%= (c == null ? "Thêm chương" : "Sửa chương") %></title>
    <style>
        body { font-family: Arial; background: #f2f2f2; }
        .container { width: 600px; margin: auto; background: white; padding: 20px; border-radius: 10px; }
        input, textarea { width: 100%; margin: 10px 0; padding: 10px; }
        button { padding: 10px 20px; background: #28a745; color: white; border: none; border-radius: 5px; }
        button:hover { background: #218838; }
    </style>
</head>
<body>
<div class="container">
    <h1><%= (c == null ? "Thêm chương mới" : "Cập nhật chương") %></h1>
    <form method="post">
        <input type="hidden" name="id" value="<%= c != null ? c.getId() : "" %>"/>

        <label>Tiêu đề</label>
        <input type="text" name="title" value="<%= c != null ? c.getTitle() : "" %>" required/>

        <label>Nội dung</label>
        <textarea name="content" rows="10"><%= c != null ? c.getContent() : "" %></textarea>

        <label>Ngày tạo (yyyy-mm-dd)</label>
        <input type="date" name="dayCreate" value="<%= c != null ? c.getDayCreate() : "" %>" required/>

        <label>ID Truyện</label>
        <input type="number" name="storyId" value="<%= c != null ? c.getStoryId() : "" %>" required/>

        <button type="submit"><%= (c == null ? "Thêm mới" : "Cập nhật") %></button>
    </form>
</div>
</body>
</html>
