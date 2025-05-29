<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
  .detail-card {
    border:none;border-radius:1rem;
    box-shadow:0 8px 18px rgba(0,0,0,.06);
  }
  .detail-header {
    background:rgb(60,110,245,.08);
    border-radius:1rem 1rem 0 0;
    padding:1.5rem 2rem;
  }
  .detail-header h3{margin:0;font-weight:600;}
  .detail-body{padding:2rem;}
  pre.content{
    white-space:pre-wrap;word-break:break-word;
    background:rgb(248,249,250);border-radius:.75rem;padding:1rem;
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
