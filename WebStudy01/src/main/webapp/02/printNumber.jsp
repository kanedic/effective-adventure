<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 메소드를 선언하고 두개의 파라미터를 전달 해당 메소드에서는 값에 해당하는 h4태그 출력 -->
<%! private String pat = "<h4>%d</h4>"; %>
<%!

//4개의 scope를 이용하여 전역 멤버를 통해 데이터 공유가 불가능한 상황을 해결할 수 있음.

	private String printNumber(int min, int max){
	StringBuffer h4number = new StringBuffer();
	
	System.out.println(min);
	System.out.println(max);
	
	for(int i =min;i<=max;i++){
		h4number.append(String.format(pat, i));
	}
	return h4number.toString();
} %>

<%

	//메소드 레퍼런스
	int min= Optional.ofNullable(request.getParameter("min"))
					 .filter(mp->mp.matches("\\d+"))
					 .map(Integer::parseInt)
					 .orElse(1);
	int max= Optional.ofNullable(request.getParameter("max"))
					 .filter(mp->mp.matches("\\d+"))
					 .map(Integer::parseInt)
					 .orElse(10);
	

%>

action herf src은 url 을 받는 설정들. 이를 생략하면 현재 주소값(location.href)으로 설정이 됨
클라가 입력을 안했으면 1~10까지
<form method="post">
<!-- 클라한테 2개의 숫자를 입력받기 -->
	<input type="number" name="min" placeholder="최소숫자" value="<%=min%>">
	<input type="number" name="max" placeholder="최대숫자" value="<%=max%>">
	<button type="submit"> 전송 </button>
	
</form>

<%=printNumber(min,max) %>

<hr>첫번째 시행
<%
	String pat2 = "<h4>%d</h4>";
	StringBuffer h4number = new StringBuffer();
	for(int i =1;i<11;i++){
		h4number.append(String.format(pat2, i));
	}
%>
<%=h4number %>
<hr>두번째 시행
<%
	for(int i =1;i<11;i++){
		out.println(String.format(pat2, i));
	}
%>
<hr>세번째 시행
<!-- 출력할 때 +연산자 사용금지 포메팅 구조로  -->
<%--  <% --%>
// String num;
// String mix;

// for(int i =1;i<11;i++){
// 	num = String.valueOf(i);
// 	mix = String.format(pat, num);

<%--  	%> --%>
<%--  	<%=mix %> --%>
<%--  <% --%>
// }
<%-- %> --%>





</body>
</html>