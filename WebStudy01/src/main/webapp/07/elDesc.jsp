<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> EL (Expression Language)</h1> 
<pre>
	: scope시스템을 통해 공유되고 있는 attribute 데이터를 브라우저상에 출력하기 위한 언어로만 사용됨. ->
	.jsp \${ 속성명 } EL
	.js  \${ } template
	.xml \${ } placeholder

	: 표현식의 대체용으로 사용됨.
	<%
	String data = "콩이";
	request.setAttribute("data", "대장민");
	request.setAttribute("attr", "요청 속성");
	session.setAttribute("attr", "세션 속성");
	application.setAttribute("attr", "어플리케이션 속성");
	%>
	표현식 : <%=data %> || EL : ${data}
	<%=request.getAttribute("attr") %> / ${requestScope.attr} 
	<%=session.getAttribute("attr") %> / ${sessionScope.attr}
	<%=application.getAttribute("attr") %> / ${applicationScope.attr}
	
	EL 연산자
	산술연산자 : + (산술연산만 시행한다.) 연산에서의 우선순위는 연산자로 결정딤
	<%
	request.setAttribute("a-1", 34);
	
	%>
	
		${4+3 } , ${"4"+3 }, ${"4"+"3" } , ${"4"+a }, ${a1+a2}
	<%=((Integer)request.getAttribute("a-1"))+3 %> / ${requestScope["a-1"]+3},
	${4/3 }
	
	논리연산자 : &&(and) , ||(or) , !(not) 
		${true and true} ${dummy and true}
	비교연산자 : &gt; (gt) ,&lt; (lt) , &gt;= (ge), &le;= (le), == (eq),!= (ne)
		${3 lt 4 },	${not (3 ge 4)},
	
	단항연산자 : empty 전위연산자 - true / false 비어있는지 여부 체크 대충 null이면 true
		${ empty dummy } ${not empty dummy }
		<%request.setAttribute("dummy1", null); %>
		<%request.setAttribute("dummy2", ""); %>
		<%request.setAttribute("dummy3", "   "); %>
		<%request.setAttribute("dummy4", new ArrayList()); %>
		<%request.setAttribute("dummy5", new String[]{"element1"}); %>
		
		dummy 1 : ${ empty dummy1 } ${not empty dummy1 }
		dummy 2 : ${ empty dummy2 } ${not empty dummy2 }
		dummy 3 : ${ empty dummy3 } ${not empty dummy3 }
		dummy 4 : ${ empty dummy4 } ${not empty dummy4 }
		dummy 5 : ${ empty dummy5 } ${not empty dummy5 }

	삼항연산자 : 조건식 ? 참 표현식 : 거짓 표현식
		${empty dummy6 ? "더미6은 없음":"있음" }
	
</pre>
</body>
</html>












