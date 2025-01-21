<%@page import="kr.or.ddit.db.ConnectionFactoryCP"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>어플리케이션의 성능 체크</h3>

	<pre>
	공간복잡도 = 최소한의 메모리
		String(상수) vs StringBuffur(객체)
	
	시간복잡도 최소한의 소요시간
		전체 소요 시간 : processing time + Latency time
<!-- 	커넥션을 미리 생성하고 모아둔 후 요청이 들어오면 미리 생성한 커넥션을 건내야함 -->
	커넥션 풀링 프레임 워크
		<%
	// 		case1 :	Member 테이블에서 a001 사용자의 이름과 휴대폰 번호 조회.(1 회) 218 =>10 ms //// 200=>0
	// 		case2 :	Member 테이블에서 a001 사용자의 이름과 휴대폰 번호 조회.(100 회) 1234 ms->900 ms; ////275=>20
	// 		case3 :	Member 테이블에서 a001 사용자의 이름과 휴대폰 번호 조회.(1 회), 출력 (100 회) 180 -> 17
	//
	long start = System.currentTimeMillis();

	for (int i = 1; i <= 100; i++) {

		try (
			Connection conn = ConnectionFactoryCP.getConnection();
			Statement stmt = conn.createStatement();) {
			String sql = "SELECT MEM_NAME, MEM_HP FROM MEMBER WHERE MEM_ID='a001'";

			ResultSet rs = stmt.executeQuery(sql);
			Map<String, String> result = new HashMap<>();
			if (rs.next()) {
				result.put("memName", rs.getString(1));
				result.put("memHp", rs.getString(2));
			}
			out.println(result);
		}//try
	}//for end

	long end = System.currentTimeMillis();
	%>	
		소요 시간 : <%=end - start%> ms
</pre>

</body>
</html>
\













