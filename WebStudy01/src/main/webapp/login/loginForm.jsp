<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error{
	color: red;
	}
</style>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body>
<!-- 톰켓한테 인증 시스템을 대리할때 사용하는 방법
<form action="j_security_check" method="post"> 톰켓 전용 주소
	<pre>
		<input type="text" name="j_username" placeholder="username">
		<input type="password" name="j_password" placeholder="password">
		<button type="submit">Login</button>
	</pre>
</form>
 -->
<form action="<c:url value='/login/loginCheck.do'/>" method="post">
	<pre>
		<input type="text" name="memId" placeholder="username"><span class="error">${errors.memId }</span>
		<input type="password" name="memPass" placeholder="password"><span class="error">${errors.memPass }</span>
		<c:remove var="errors" scope="session"/>
		<button type="submit">Login</button>
	</pre>
</form>



</body>
</html>