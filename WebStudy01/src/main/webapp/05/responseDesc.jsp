<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> HttpServletResponse 객체의 활용 (http response packaging)</h3>
<pre>
	response : 서버가 클라이언트로 전송한느 메시지 전체를 캡슐화한 객체
	 └ 1. Response Line : 
	 	Status Code (상태 코드) - 요청 처리의 결과를 표현할 수 있는 세자리 숫자 코드
	 	  └1) 1xx : ING... 
	 	  		HTTP : (ConnectLess, StateLess)
	 	  		 Connect-less : 연결이 의도적으로 수립,종료되지 않는 구조 ex)HTTP 서버의 부하 ↓
	 	  		 		실시간 양방향 통신이 불가능한 구조 -->극복법 Long Polling 방식 , WebSocket(Connect-full[101 상태코드]))
	 	  		 Connect-full : 연결(connect)을 필요에 따라 의도적으로 수립하고 종료할 수 있는 방식 ex)데이터베이스 프로그래밍
	 	   2) 2xx : OK 정상처리
	 	   3) 3xx : 요청 처리는 아직 완료되지 않았고, 클라이언트의 추가 액션이 필요함을 표현. response의 body가 존재하지 않음.
	 	   	    304 : Not Modified : 캐싱된 정적 자원은 최종버전과 동일하니 캐싱 자원을 사용하라는 표현
	 	   	    307/302/301 : MOVED, 최종 자원의 위치가 변경되었음에 대한 표현
	 	   	    				"Location(변경된 위치)" 응답 헤더와 함께 전송됨
	 	   4) 4xx : Failure, 클라이언트측 오류
<%-- 	 	   	  <%=HttpServletResponse. %> --%>
	 	   	    400	: Bad Request : request가 유효성 검증을 통과하지 못한 경우
	 	   	    	ex) 필수 파라미터 누락, 요청 데이터 길이의 허용치 초과
	 	   	    		요청에 포함된 데이터의 타입이 잘못된 경우
	 	   	    401/403 : 401 UnAuthorized 아직 인증 전(로그인 상태) 확인 403 Forbidden 권한 없음
	 	   	    404 : Not Found 
	 	   	    405 : Method-Not-Allowed : 클라이언트의 request method를 처리할 수 없음
	 	   	    406 : Not-Acceptable : 응답 컨텐츠를 협상하는 조건으로 사용되는 "accept" 헤더로 결정된
	 	   	    						컨텐츠의 종류를 서버가 처리할 수 없을 때 사용
	 	   	    415 : UnSuppoted-Media-Type : 클라이언트가 전송한 body 컨텐츠를 서버에서 파싱할 수 없을 때 
	 	   	    <a href="status400.jsp">400번대 테스트 페이지</a>
	 	   	    
	 	   5) 5xx : Failure, 서버측 오류 500 (Internal Server Error[서버에 오류 발생], 서버의 정보 노출 제한)
	 
	 
	 └ 2. Response Headers : setContentType , setContentLength,=>> setHeader(name,String)
	 		1) Content-Type, Content-Length (공통 헤더) : body의 content에 대한 메타 데이터. body를 수식 / 클라쪽에서의 소비 형식을 결정
				Content-Type(body content 종류)
				Content-Length(body content 길이)<%--	<%response.setContentLength(200);%> --%>
			2) Content-Disposition (공통 헤더) 
				request header : method="post" content-type="multipart/form-data" 
									body의 부분집합(part) 하나에 대한 메타데이터로 사영됨
								ex) 문자 기반 - Content - Disposition : form-data; name="파트명"
								ex) 파일 기반 - Content - Disposition : form-data; name="파트명"; filename="파일명"
				response header ==== attatchment로 진행
					Content-Disposition
					 - inline(default) : 브라우저의 창 내부에서 웹 페이지의 형태로 컨텐츠를 소비
					 - attatchment : 다운로드 받고 저장 하라는 뜻 저장명은 ;filename="파일명" 으로 지시를 해야함
									 단, 파일이름에 특문, 공백이 url encoding 방식이나 replace 구조가 필요함
									 
<%-- 				<% --%>
<!-- // 					String filename = "더미 1.html"; -->
<!-- // 					filename = URLEncoder.encode(filename,"utf-8").replace("+"," "); -->
					
<!-- // 					response.setHeader("Content-Disposition", "attatchment; filename=\""+filename+"\""); -->
<%-- 				%> --%>
				
			3) Refresh [자원 갱신] (response 전용)	<a href="refresh-auto-request.jsp"> refresh header </a>	 
	 		4) Cache-Control [캐시를 컨트롤] (공통 헤더) <a href="cacheControl.jsp"> cacheControl header </a>	 
	 			 
	 		5) Location (response 전용) <a href="flowControl"> "flowControl" header </a>	 
	 
	 └ 3. Response Body(Message Body, Content Body) :
		  └ servlet : response.getWriter(),response.getOutputStream()
		  └ jsp : 표현식 , out 객체
</pre>

<form action="" method="post" enctype="multipart/form-data"> 멀티파트는 공백이여도 무조건 전송이 됨. 서버에선 무조건 체크해야함
<input type="text" name="param1">
<input type="file" name="uploadFile">
	<button type="submit">전송</button>
</form>
</body>
</html>






