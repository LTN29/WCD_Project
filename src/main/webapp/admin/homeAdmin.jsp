<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags"%>
<admin:_layoutAdmin>

	<div
		style="font-size: 18px; color: #007bff; font-weight: 600; text-align: center; margin-top: 16px;">
		Xin chào,${sessionScope.user.name}
	</div>

</admin:_layoutAdmin>