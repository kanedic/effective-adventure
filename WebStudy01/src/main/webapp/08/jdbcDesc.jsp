<%@page import="kr.or.ddit.db.ConnectionFactory"%>
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
// 	1.드라이버를 빌드패스에 추가(벤더[제조사] 제공) → driver loading 작업
// 	2.Connection 생성
	try(
		Connection conn= ConnectionFactory.getConnection();
	
// 	3.Query 쿼리 객체 생성
		Statement stmt = conn.createStatement();
			){
		
// 	4.쿼리 실행
	String sql = "select mem_id, mem_name from member";
	ResultSet rs = stmt.executeQuery(sql);	//db에서 조회된 결과는 포인터를 cursur의 형태임

// 	5.쿼리 결과 핸들링
	Map<String,String> resultMap = new HashMap();
	request.setAttribute("resultMap", resultMap);
	while(rs.next()){
		resultMap.put(rs.getString("mem_id"),rs.getString("mem_name"));
		}
	}	
	
	%>
	6.사용한 자원 release (close)
	
	${resultMap.size() }
	
</pre>
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
			<th><a href="detail.jsp?who=${entry.key }"> ${entry.value }</a> </th>
		</tr>			
		</c:forEach>
	</tbody>
</table>


<table>
	<thead>
		<tr>
		<th>아이디</th>
		<th>이름</th>
		</tr>
	</thead>
	<tbody>
		<c:forTokens items="${resultMap }" delims="," var="oneMap">
			<c:forTokens items="${oneMap }" delims="=" var="twoMap">
				<tr>
					<th>${twoMap }</th>
				</tr>						
			</c:forTokens>
		</c:forTokens>
		
	</tbody>
</table>


</body>
</html>







