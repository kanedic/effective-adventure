<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 	스타일 코드 */
</style>
<script type="text/javascript">
// JS 코드
</script>
</head>
<body>

<h4> JSP </h4>
<pre> 

WHY : 서블릿 스펙의 단점을 보완하며, Model2 아키텍처를 적용할 때, view 레이어를 쉽게 구성할 수 있는 템플릿 엔진이 활용
	 최종 서비스 컨텐츠의 타입이 text 기반의 MIME을 갖는 경우, 템플릿 엔진들 (jsp,thymelaf, velocity,freemarker)  이 활용됨.
	 
	 템플릿 엔진이란? : 템플릿 파일(html,jsp)과 데이터(controller)를 결합시키는 결합자
	 
JSP (Java Server Page) ? : 자바 기반의 서블릿 스펙에서 파생된 서버사이드 템플릿 엔진.

HOW : 정적인 템플릿 요솔르 제외한 동적 요소를 작성하는 방법

*** jsp의 표준 구성 요소 
 1. 정적 요소 (front-end) : 일반 text 문자 ,html 소스, CSS , JavaScript => Client Side 언어 
 2. 동적 요소 (back-end): 
 	1) scriptlet  : 자바의 _jspService 메소드의 지역 코드로 전환되는 자바 코드
 					해당 메소드에 지역 변수형태로 선언된 기본 객체 사용 가능.
 	
 	<% 
						String data = "이것도 데이터";	
//  						return;
				 	%>
 	2) expression : <%="출력값" %> out.write(출력값) 으로 전환됨.
 	3) direcive   : <%@ page buffer="8kb" %> 실행코드에는 영향 X JSP파일의 환경설정을 할 때 사용 속성으로 설정변경
 					page (required) : 웹 페이지 자체의 설정에 영향을 끼치는 설정
 					include (optional) : 정적 내포
 					taglib (optional) : custom tag 사용시 필요
 	4) declaration: 클래스 차원의 전역 코드로 전환되는 자바 코드.
 	 <%! 
						//전역코드로 변수 선언
						String data="데이터"; 

					%>
 	5) comment    : <%-- 주석 --%>
 		-back end comment: (***) : java,jsp의 주석
 		-front end comment: (XXX) : HTML,CSS,JS의 주석
 	
 	JSP 컨테이너의 역할 단계
 	- jsp 템플릿 파일을 대상으로 최초의 요청이 발생하면, 템플릿 소스를 파싱하여 java코드를 정의함.(.java 서블릿 작성)
 	- java파일 compile (.class 의 바이트 코드 생성)
 	- 싱글톤 인스턴스 생성
 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ최초 실행 [하나라도 수정되면 다시 최초 실행]
 	.
 	- _jspService 메소드 실행과 응답을 처리함.
 	
 	
 	
	6) EL(Expression ianguage)
	7) Custom tag (JSTL)


</pre>
</body>
</html>