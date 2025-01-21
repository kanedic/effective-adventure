<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>웰컴 페이지 입니다</h1>
	현재 사용자가 인증된 상태라면 신원 정보를 출력하고 그게 아니라면 로그인 페이지 링크를 출력함 로그아웃 클릭- 서블릿 인벨리드
	- index로 넘어옴 세션에 저장된 값이 있으면 신원정보 없으면 페이지링크

	<%
	Principal userPrincipal = request.getUserPrincipal();

	if (userPrincipal == null) {
	%>
		<a href="${pageContext.request.contextPath}/login/loginForm.jsp">로그인</a>
	<%
	} else {
	%>
	<h4>
		현재 사용자 :
		${pageContext.request.userPrincipal }
		<%=userPrincipal%></h4>
<%-- 		<form id="logoutForm" action="<%=request.getContextPath()%>/login/logout.do" method="post"> --%>
		<form id="logoutForm" action="${pageContext.request.contextPath }/login/logout.do" method="post">
		</form>
		a태그 클릭하면 포스트 방식으로 이벤트 발생하도록
		<a href="javascript:postFetch();"> 로그아웃 </a>
		<a href="javascript:logoutForm.requestSubmit();"> Teacher 로그아웃 </a>
	<%
	}
	%>
<c:if test="${empty authMember }">
		<a href="${pageContext.request.contextPath}/login/loginForm.jsp">로그인2</a>	
</c:if>
<c:if test="${not empty authMember }">
		<h1>EL 현재 사용자 : ${userPrincipal },${authMember }</h4>
		<a href="${pageContext.request.contextPath}/login/logout.do">EL Teacher 로그아웃 </a>
<!-- 		<a href="javascript:logoutForm.requestSubmit();">EL Teacher 로그아웃 </a> -->
</c:if>

<%-- <c:set var="userPrincipal" value="${pageContext.request.userPrincipal }"></c:set> --%>
<%-- 	<c:if test="${empty userPrincipal }"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/login/loginForm.jsp">로그인2</a>	 --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${not empty userPrincipal }"> --%>
<%-- 		<h1>EL 현재 사용자 : ${userPrincipal }</h4> --%>
<!-- 		<a href="javascript:logoutForm.requestSubmit();">EL Teacher 로그아웃 </a> -->
<%-- 	</c:if> --%>

</body>
<script type="text/javascript">
function postFetch(){
	
	var form = document.querySelector("form");
	var url = form.action
	
// 	console.log(form)
	
	fetch(url,{
		method:"post"
	})
	.then(resp=>location.href="<%=request.getContextPath()%>")
	
	
}
</script>

</html>





