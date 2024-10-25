<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3> 웹 에서의 흐름 제어 방식</h3>

<pre>
	Http의 특성
		Connect-less : response가 수신 되면 , 유지되던 connection은 close가 됨. 
		State - less : request/response 지닌 정보를 잃고 객체는 삭제됨.

	1. Request Dispatch : 최초로 발생한 요청이 이동하는 (흐름이 제어되는 구조에서) 그대로 유지되는 방식.
		1) forward : 요청과 응답에 대한 처리자가 분리되는 구조 ==> Model2 아키텍처에서 사용됨
		2) include : 하나의 응답 페이지를 구성하기위한 책임이 두개 이상의 객체로 분리된 구조 ==> 페이지 모듈화 구조에서 사용됨 요청 위임방식
		
		<%--
		
		RequestDispatcher rd = application.getRequestDispatcher("/06/dest.jsp");
		
//		RequestDispatcher rd= request.getRequestDispatcher("/06/dest.jsp");
// 		rd.forward(request, response); //기존의 요청과 응답을 가져감
		rd.include(request, response); //기존의 요청과 응답을 가져감	--%>
<%-- 	<jsp:forward page="/06/dest.jsp"></jsp:forward> --%>
<%-- 		<jsp:include page="/06/dest.jsp"/> 액션태그 --%>
		
		
	2. Redirect : 자원의 위치를 재지정할때 PRG [Post Redirect Get] 패턴의 구조에서 활용됨
		1) 최초의 요청 발생
		2) body가 없는 응답이 전송되며 307/302 코드, Location헤더 포함
		==> Connection close , StateLess
		3) Location 헤더 방향으로 새로운 요청이 전송됨
		4) 최종 응답 전송
		
		<%--//로케이션 헤더의 주소를 주어야함
		String location = request.getContextPath()+"/06/dest.jsp";
			response.sendRedirect(location);
		--%>
		
</pre>

</body>
</html>







