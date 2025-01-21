<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>JDBC (Java DataBase Connectivity)</h2>

	<pre>
	JDBC 프로그래밍 단계
	java.sql 패키지의 인터페이스 구현체 : 드라이버
	
	<%
	String memId = request.getParameter("who");
	System.out.println(memId);
	if(StringUtils.isBlank(memId)){
		response.sendError(400);
		return;
	}
	
	Connection conn= ConnectionFactory.getConnection();
	
	String sql = "select mem_id, mem_name,mem_hp,mem_add1 from member where mem_id = ? ";
// 	2.Connection 생성
	try(
// 	3.Query 쿼리 객체 생성
// 		Statement stmt = conn.createStatement();
		PreparedStatement stmt = conn.prepareStatement(sql);
			
			){
		
// 	4.쿼리 실행
	stmt.setString(1, memId);
	ResultSet rs = stmt.executeQuery();	//db에서 조회된 결과는 포인터를 cursur의 형태임

// 	5.쿼리 결과 핸들링
	Map<String,String> resultMap = new HashMap();
	request.setAttribute("resultMap", resultMap);
	if(rs.next()){
		resultMap.put("mem_id",	rs.getString("mem_id"));
		resultMap.put("mem_name",	rs.getString("mem_name"));
		resultMap.put("mem_hp",	rs.getString("mem_hp"));
		resultMap.put("mem_add1",	rs.getString("mem_add1"));
				
		}
	}	
	
	%>
	6.사용한 자원 release (close)
	
	${resultMap.size() }
	
</pre>
${resultMap }
<table>
	<thead>
		<tr>
		<th>아이디</th>
		<th>이름</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${resultMap }" var="entry">
		<tr>
			<th>${entry.key }</th>
			<th><a href="detail.jsp?who=${entry.value }"> ${entry.value }</a> </th>
		</tr>			
		</c:forEach>
	</tbody>
</table>

</body>
</html>







