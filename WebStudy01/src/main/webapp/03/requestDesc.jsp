<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4> Http Request</h4>
응답의 희망사항 Accept
클라이언트의 os 사이트 종류 사용환경 user Agent
응답의 정보를 담은 content
<pre>

	Http (Hyper Text Trasnfer Protocol) : 웹 상의 클라이언트와 서버가 메시지를 교환할 때 사용하는 패키징 방식.
	
	명령 - Http request packaging : 클라이언트가 사용자에게 전송하는 우편물 ==> HttpServletRequest로 캡슐화 됨
		1. Request Line : URL (수신자), http method로 요청의 목적과 패키징 방식을 추가로 표현. 명령을 표현
			URL (Uniform Resource Locator) : <%=request.getRequestURL() %> 전체경로  
			URI (Uniform Resource Identifier): <%=request.getRequestURI() %> origin이 빠짐 / 클라이언트사이드 절대 경로 형태
			servlet path : <%=request.getServletPath() %> URI 에서 contextPath가 빠짐 / 서버사이드 절대 경로 형태
			
			URI 내부에 URL이 존재함.
			
			URI (Uniform Resource Identifier) :	자원을식별하기 위한 식별자의 총칭
			URN	(Uniform Resource Name) : ex) 소년이 온다. 식별성이 떨어짐
			URC (Uniform Resource Content) : ex) 저자 : 한강 , 출판사 : 창비, 노벨문학상 작가의 작품 식별성이 떨어짐
			URL (Uniform Resource Locator) : ex) 교보문구 4층 소설코너 두번째 책장의 세번째 줄에서 네번째 칸의 왼쪽에서 세번째 책.
			
			
			request method : <%=request.getMethod() %> [현재 request의 요청 메소드의 방식]
			
			└ url의 형태
				protocol[scheme]://IP[Domain-host]:port/pathname?queryString
					ex)https://www.example.com:443/depth1/depth2/filename.exp?k1=v1&k2=v2...
					origin (protocol + host + port) + resource path(depth) + queryString
					  └[동일 출처의 경우 생략이 가능] 
			└ http method
				└GET (R,****): 서버의 자원 조회 , src , href 속성에 의해 발생하는 요청. [디폴트값] 
				└POST (C) : 서버에 새로운 자원 등록
				PUT / PATCH (U) : 서버의 자원을 갱신 (수정) 수정 대상의 모든 값을 수정 put
						수정의 대상만 수정
				DELETE (D) : 서버의 자원을 삭제
				OPTIONS : 서버의 상황을 확인하기 위한 preFlight 요청에 사용됨
				HEAD : 응답데이터에 content body가 없는 응답을 수신하기 위한 요청에 사용됨.
				별로 안쓰는 TRACE : 제한된 환경에서 서브를 트래킹할 목적의 요청에 사용됨.
				
				테스트 자원의 URL 설계 - RestFull URI 방식의 설계
				서버사이드절대경로
				/person (GET) : 전체 person 조회
				/person/p001 (GET) : 단건(短件)(person) 조회
				/person (POST) : 새로운 person 등록
				/person/p001 (PUT) : p001의 정보 수정
				/person/p001 (DELETE) : p001의 정보 삭제
			
				<button class="test-btn" data-method="get" >GET 요청</button>
				<button class="test-btn" data-method="post">POST 요청</button>
				<button class="test-btn" data-method="put" >PUT 요청</button>
				<button class="test-btn" data-method="delete">DELETE 요청</button>
				<button class="test-btn" data-method="head">HEAD 요청</button>
				<button class="test-btn" data-method="options">OPTIONS 요청</button>
				
			
		2. Request Header : 클라이언트와 요청에 대한 정보를 수식할 수 있는 메타데이터. (name/String value)로 구성되어 있음.
			1)Accept-* : 응답(response)의 조건을 설정할 때 활용됨.
				Accept-Language : 응답 메시지를 구성할 언어(Locale)
				Accept-Encoding : 응답 메시지의 압축 형식
				Accept : 응답 메시지의 종류 (Mime Text)
			
			2)content-* : request body가 존재하며 body를 통해 전동되는 메시지가 있을 때 ,
							해당 메시지의 종류(Content-Type)나 길이(Content-length)를 표현할 수 있음
			3)user Agent :클라이언트의 사용기기와 브라우저의 종류
			<a href="<%=request.getContextPath()%>/03/requestHeader.jsp"> request header</a>
			
		3. Request Body(meessage body , content body) : 클라이언트가 서버로 전송할 메시지 
			1) 파라미터(문자열로 전송) 형태의 form-data(****):  application/x-www-form-urlencoded [디폴트 값] [파라미터 전송]
			2) 멀티파트 형태의 form-data :  multipart/form-data 파일 전송등 [파일,문자,Json] 등등 여러 데이터를 한번에 전송
			3) json[xml] payload : application/json[xml] (데이터의 형태를 변환하지않고 전송하고싶을때)
<%-- 			<a href="<%=request.getContextPath()%>/03/requestData.jsp"> request content</a> --%>
 
</pre>
<a href="">GET 요청 </a>
<form action="" method="post" enctype="application/x-www-form-urlencoded">
	<button type="submit" >POST 전송 </button>
</form>

<button type="button" id="putbtn">PUT 전송</button>
<script type="text/javascript">
	
	putbtn.addEventListener("click",()=>{
		fetch("",{method:"put"});
	})

	document.querySelector("pre").addEventListener("click",(e)=>{
		//test-btn라는 클래스가 존재하지 않으면 false
		
		console.log("das")
		if(e.target.classList.contains(".test-btn"))return false;
		
		let method=e.target.dataset.method;
		let url = "<%=request.getContextPath()%>/person"
// 		console.log(method)
		
		
		fetch(url,{method:method});
		
	})	
	
</script>


<%-- <img alt="" src="<%=request.getContextPath() %>/resources/images/cat1.jpg"/> --%>


</body>
</html>