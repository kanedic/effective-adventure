<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3> JSP 스펙에서 제공되는 기본 객체의 종류</h3>
	<pre>
	request(HttpServletRequest) : 클라이언트와 클라이언트부터 발생한 요청에 대한 정보를 가진 객체
	response(HttpServletResponse) : 서버에서 클라이언트로 전송되는 응답에 대한 정보르 가진 객체
	
	***
	session(HttpSession) : 하나의 클라이언트로 부터 발생한 모든 세션 정보를 가진 객체 
		Connect-Full(DB) : 클라이언트와 서버 사이의 유일한 연결 통로
		Connect-Less(WEB) : 클라이언트가 어플리케이션을 사용하기 시점 - 종료 이벤트를 발생시켰을 때의 기간
		<a href="sessionDesc.jsp">Http Session</a>
	
	application (ServletContext): 서버와 현제 컨텍스트에 대한 정보를 가진 객체 [컨텍스트의 갯수에 따라 결정]
		[동일 컨텍스트 내에서 싱글톤의 형태로 관리됨] 
		서버 버전 정보 : <%=application.getServerInfo() %>
		서블릿 스펙 버전 : <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
		MimeType 조회 : test.jpg = <%=application.getMimeType("test.jpg") %>
		Real path : <%=application.getRealPath("/08/test.html") %>
		context path : <%=application.getContextPath() %>
		
	 이 안에 위의 객체를 모두 소유하고있음
	pageContext(PageContext) : jsp와 관련된 모든 정보를 가진 객체로 나머지 모든 기본 객체를 소유하고 있음. 
	<%
 		request.setAttribute("requestAttr", "요청속성");
//	 	pageContext.setAttribute("requestAttr", "pageC로 저장", pageContext.REQUEST_SCOPE);
	%>
	<%--request.getAttribute("requestAttr")--%>
	<%=	pageContext.getAttribute("requestAttr",pageContext.REQUEST_SCOPE)%>
	
	<%
		pageContext.removeAttribute("requestAttr",pageContext.REQUEST_SCOPE);
	%>
	
	remove => : <%=	request.setAttribute("requestAttr", "요청속성")%>
	</pre>

<h3> Scope</h3>
	<pre>
		: 데이터 저장 공간(Map &lt;String,Object&gt;). 해당 공간에 저장된 데이터를 attribute(name,value)라고 칭함.	
	
	request scope : request 객체와 생명주기가 동일한 Map
	session scope : session 객체와 생명주기가 동일한 Map
	application scope : servlet context 객체와 생명주기가 동일한 Map
	
	setAttribute(name,value) ,Object getAttribute(name) ,removeAttribute(name) 
	</pre>
</body>
</html>



















