<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<!-- <meta http-equiv="refresh" content="1,url=https://www.naver.com">refresh 헤더를 지닌것처럼 행동함 -->
<title>Insert title here</title>
</head>
<body>
<h3> Refresh header의 사용</h3>
<pre>
	: 주기적(second 단위)으로 서버에 대한 요청을 전송할 때 활용됨 
	화면 전체를 refresh 하기 위해 동기요청을 전송함, 비동기에 대한 요청은 발생하지 않음
	ex) reflesh=10,url=외부 사이트주소 일정 시간이 지연된후 외부사이트로 redirection 할 수 있음 (TimeOut 10초, location.href=주소)
	
	서버사이드 처리 : refresh헤더 사용
	클라이언트 사이드 처리 : html meta 태그 가짜 헤더를 생성하여 있는 것처럼 ,JS 스케쥴링 함수

<%--
	response.setHeader("refresh", "5,url=https://www.naver.com");
--%>

현재 서버의 시간 : <span id="server-area"> </span>
<input type="text" value="asd">
<span class="has-watch"> </span>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/05/watchLib.js"></script>

</pre>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded",async ()=>{
		setTimeout(() => { //1초마다 새로고침
// 			location.reload();
			location.href="https://www.naver.com";
		}, 50000);
		
		
		const serverArea=document.getElementById("server-area")
		
		let resp = await fetch("getServerTime.jsp")
	
		let textTime = await resp.text();
		
		serverArea.innerHTML=textTime;
		
	})
	
	
</script>
</body>
</html>




















