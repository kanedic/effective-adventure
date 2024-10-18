<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<h4> request header</h4>

<form action="" method="post">
<input type="text" name="key1" value="value1">
<input type="text" name="key2" value="한글값">
<input type="text" name="key3" value="中">
	<button type="submit"> Post 전송</button>
</form>

<pre>
request를 확보하는법
 	Accept : <%=request.getHeader("Accept") %>
	Accept-encoding : <%=request.getHeader("Accept-encoding") %>
	Accept-language : <%=request.getHeader("Accept-language") %>

 	Content-Type : <%=request.getHeader("Content-Type") %>
 	Content-length : <%=request.getHeader("Content-length") %>

	
</pre>

<table>
헤더의 으름을 몰라도 가져오기
<thead>
	<tr>
		<th>헤더이름</th>
		<th>헤더값</th>
	</tr>
</thead>
	<tbody>
	<%
// 	Collection : List, Set, Map
// 	Collection view : 순차접근이 불가능한 컬렉션[set,map-을 순차적인 접근방법을 정의하고 있는 객체 (Iterator,Enumeration)
	
	
		Enumeration<String> headerNames= request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headName = headerNames.nextElement();
			String value=request.getHeader(headName);
			
			
			%>
	<tr>
	<td><%=headName %></td>
	<td><%=value %></td>
	</tr>
			<%
		}
	%>
	
	</tbody>


</table>


<body>
</body>
</html>