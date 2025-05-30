<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
  body {
  background-color: #121212;
  color: #f1f1f1;
}
.detail-card {
  background-color: #1e1e1e;
  border-radius: 1rem;
  box-shadow: 0 0 15px rgba(255, 255, 255, 0.05);
}
.detail-header {
  background-color: rgba(60, 110, 245, 0.12);
}
.badge.bg-primary {
  background-color: #3c6ef5;
}
pre.content {
  background-color: #2a2a2a;
  color: #eee;
  border-radius: 0.75rem;
}
.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

</style>

<admin:_layoutAdmin>
<div class="container py-4">

  <a href="chapter" class="btn btn-secondary btn-sm mb-3">
    <i class="bi bi-arrow-left"></i> Quay lại
  </a>

  <div class="detail-card">
    <div class="detail-header d-flex justify-content-between align-items-center">
      <h3>${chapter.title}</h3>
      <span class="badge bg-primary">#${chapter.id}</span>
    </div>
    <div class="detail-body">
      <p><i class="bi bi-calendar"></i> Ngày tạo:
        <strong>${chapter.dayCreate}</strong></p>
      <p><i class="bi bi-book"></i> Mã truyện:
        <strong>${chapter.storyId}</strong></p>

      <hr>
      <h5>Nội dung</h5>
      <pre class="content"><c:out value="${chapter.content}" /></pre>
    </div>
  </div>

</div>
</admin:_layoutAdmin>
