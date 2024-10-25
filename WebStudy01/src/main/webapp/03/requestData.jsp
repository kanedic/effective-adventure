<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4> 요청 데이터의 종류</h4>
<a href="?key1=한글&key2=value2">GET 전송</a>
<form action="" method="post" enctype="application/x-www-form-urlencoded">
	<input type="text" name="key3" value="한글"/>
	<input type="text" name="key4" value="value4"/>
	<button type="submit">POST 전송하기</button>
</form>


<pre>
쿼리 스트링은 꼭 get방식에만 사용할 수 있는 것은 아님

	1.query string : request line을 통해 전달되는 문자열 집합.
		request.getQueryString() : <%= request.getQueryString() %>
		request.getParameter() : <%= request.getParameter("") %>
	2.body content : 
		1) 파라미터 form-data(****) : application/x-www-form-urlencoded [특수문자 : 영어/숫자 를 제외한 모든 언어]
			urlencoded : 웹 상에서 문자열의 형태로 특수문자를 전송할 때 사용되는 인코딩 방식(url encoding)을 인코딩된 텍스트
			ex) key1=value1 → key1 = <%=URLEncoder.encode("value1","utf-8") %>
			ex) key2=한글value2 → key2 = <%=URLEncoder.encode("한글value2","utf-8") %>
			
			==> 요청 파라미터로 통일화된 방식으로 확보 : 문자열(String) 형태로만 전송됨.
			    String param1 = getParameter(name);
			    String[] param2 = getParameterValues(name); -동일한 Key 값으로 전달되는 여러 값들을 반환 그렇기에 배열이 필요.
				Map&lt;String,String[]&gt;param3 = getParameterMap(); : 파라미터 전체가 관리되는 map 반환
				Enumeration&lt;String&gt;param4 =  getParameterNames();
				
				<%request.setCharacterEncoding("UTF-8"); %>
				
				key1 (line) : <%=request.getParameter("key1") %>
				key3 (body) : <%=request.getParameter("key3") %>
				
				
		2) 멀티파트 form-data : <a href="<%=request.getContextPath()%>/03/multipartDesc.jsp">Multipart Content</a> 
		
		
		3) json[xml] payload: <a href="<%=request.getContextPath()%>/03/jsonAndRest.jsp">JSON and REST</a>

자원의 출처 오리진
동일 출처의 원칙으로 요청의 출처가 동일하다면 생략가능

</pre>

<pre>
	key 1 : <%=request.getParameter("key1") %>
	key 2 : <%=request.getParameter("key2") %>
	key 3 : <%=Arrays.toString(request.getParameterValues("key3")) %>

</pre>


</body>
</html>

