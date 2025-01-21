<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>sessionDesc.jsp</h3>
<pre>
세션은 식별성이 있어야 하며 이는 id로 식별함
세션의 아이디는 클라이언트에도 저장되어야함

세션의 아이디를 추적하기 위한 request에는 아이디를 포함

서버의 세션중 한 클라이언트와 연관된 세션을 찾기위해 ID를 찾는 방식 - 세션 트래킹 모드
요청에 만약 트래킹 모드로 사용할만한 id가 존재하지 않으면 최초의 요청으로 판별

응답에 새로 생성한 id를 저장 후 전달. 클라는 헤더를 읽고 세션id를 저장함.
</pre>



<pre>
	세션의 생명주기
		생성 시점 (시작) : 세션 트래킹 모드로 전달되는 세션 아이디가 없는 요청이 발생했을 때
			==> 세션의 식별자로 ID를 부여받음. 
				└ session id : <%=session.getId() %>
				└ session 생성시점 : <%=new Date(session.getCreationTime()) %>
				└ session id : <%=session.getId() %>
			==> response 에 ID가 포함되어 클라이언트쪽으로 전송됨.
			==> 클라이언트가 해당 ID를 자신의 저장소에 저장함.
			==> 다음 요청에 해당 ID를 포함하여 서버로 전송.
				└ session 마지막 요청시점 : <%=new Date(session.getLastAccessedTime()) %>

		session tracking mode : 클라이언트와 서버가 세션 아이디를 서로 교환하는 방법.
		 	1) COOKIE (★★★★) 
		 	2) URL : <a href="sessionDesc.jsp;?jsessionid=<%=session.getId() %>">현재 페이지로 다시 요청 전송</a> 매트릭스 변수 세션 파라미터
		 	3) SSL (Secure Socket Layer) : C와 S 사이의 메시지를 암호화 하는 방식 

		소멸 시점 (종료) 
			1) timeout(<%=session.getMaxInactiveInterval() %>s) 이내에 새로운 요청이 전송되지 않은 경우 - 불분명한 방법
				- cookie 제거
				- 브라우저 완전종료
			2) 직접적인 로그아웃 <%--session.invalidate(); --%>


</pre>

</body>
</html>














