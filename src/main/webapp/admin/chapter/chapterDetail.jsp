<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

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
