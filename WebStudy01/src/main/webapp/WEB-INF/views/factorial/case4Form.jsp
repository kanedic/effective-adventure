<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="facForm" method="post">
	<input type="number" name="operand" required="required" onchange="this.form.requestSubmit();">
</form>
 form 태그는 submit이벤트에서 동기 요청을 발생시킨다
 자원과 자원을 사용할수 있는 권한을 지닌 락

동기요청 - 락을 지닌 

<div id="resultArea"></div>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/factorial/case4Form.js"></script>

</body>
</html>