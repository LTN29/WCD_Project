<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags"%>
<client:_layoutClient>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/faq.css" />
    <div class="faq-container">
        <h2><i class="fa fa-question-circle text-primary"></i> HỎI ĐÁP - CÂU HỎI THƯỜNG GẶP</h2>
        <div class="faq-list">
            <div class="faq-item">
                <b>1. Làm sao để đăng truyện lên trang web?</b>
                <div>Đăng nhập, vào mục “Đăng truyện”, điền thông tin truyện và nhấn “Gửi”. Truyện sẽ được kiểm duyệt trước khi xuất hiện.</div>
            </div>
            <div class="faq-item">
                <b>2. Tôi quên mật khẩu thì làm sao lấy lại?</b>
                <div>Nhấn vào “Quên mật khẩu” trên trang đăng nhập, điền email đăng ký. Hệ thống sẽ gửi hướng dẫn khôi phục.</div>
            </div>
            <div class="faq-item">
                <b>3. Tôi có thể bình luận ở đâu?</b>
                <div>Bạn có thể bình luận dưới mỗi chương truyện hoặc dưới mục HỎI ĐÁP này!</div>
            </div>
            <div class="faq-item">
                <b>4. Vì sao truyện của tôi chưa được duyệt?</b>
                <div>Mỗi truyện sẽ được kiểm tra bởi admin. Nếu chưa hợp lệ, bạn sẽ nhận thông báo chỉnh sửa trong vòng 24h.</div>
            </div>
            <div class="faq-item">
                <b>5. Làm sao để liên hệ góp ý cho web?</b>
                <div>Gửi email về <a href="mailto:ad.WCD1_Test1@gmail.com">ad.WCD1_Test1@gmail.com</a> hoặc bình luận tại đây.</div>
            </div>
            <div class="faq-item">
                <b>6. Có giới hạn số chương/truyện đăng không?</b>
                <div>Không giới hạn. Nhưng truyện nên tuân thủ quy định cộng đồng và luật bản quyền.</div>
            </div>
            <div class="faq-item">
                <b>7. Tôi muốn tham gia Discord của cộng đồng thì làm thế nào?</b>
                <div>Truy cập <a href="/discord" target="_blank">DISCORD</a> để tham gia group trao đổi, hỏi đáp, làm quen.</div>
            </div>
            <div class="faq-item">
                <b>8. Làm sao để truyện lên trang chủ?</b>
                <div>Những truyện có lượt đọc, bình luận hoặc đánh giá cao sẽ được hệ thống tự động gợi ý lên trang chủ.</div>
            </div>
            <div class="faq-item">
                <b>9. Có thể tải truyện về đọc offline không?</b>
                <div>Hiện tại web chưa hỗ trợ chức năng tải truyện. Bạn chỉ có thể đọc online.</div>
            </div>
            <div class="faq-item">
                <b>10. Tôi phát hiện truyện vi phạm bản quyền thì làm sao báo cáo?</b>
                <div>Bạn có thể gửi email hoặc dùng chức năng “Báo cáo” dưới truyện để admin xử lý.</div>
            </div>
        </div>
        
        <!-- Block comment -->
        <div class="comment-block mt-4">
            <h5>
                <i class="fa fa-comments"></i> Bình luận Hỏi đáp (
                <c:out value="${fn:length(commentList)}" />
                )
            </h5>
            <div class="comment-list">
                <c:choose>
                    <c:when test="${not empty commentList}">
                        <c:forEach var="comment" items="${commentList}">
                            <div class="comment-item">
                                <b>${comment.userName}:</b> ${comment.content}
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="comment-empty">Chưa có bình luận nào.</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <form class="comment-input-row" action="CommentServlet" method="post">
                <input type="hidden" name="faq" value="1" />
                <input type="text" name="content" placeholder="Viết bình luận về HỎI ĐÁP..." required />
                <button type="submit">Gửi</button>
            </form>
        </div>
    </div>
</client:_layoutClient>
