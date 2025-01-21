<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>PART2 에서 만든 컨텐츠 으으으으이ㅡ으으으의으으응ㅇㅇㅇㅇ</h1>
<%-- <h3 class="text-info-emphasis"><%=request.getParameter("param") %> -- D part2</h3> --%>
<%-- <h3 class="text-info-emphasis"><%=request.getAttribute("model") %> -- D part2</h3> --%>
<h3 class="text-info-emphasis">${model},${sessionScope['new-menu'] } -- D part2</h3>

<ul>
	<li>key : value<li>
<%
	String menuName = (String)session.getAttribute("new-menu");

	Map<String,Object> recipe = (Map<String,Object>)request.getAttribute("model");

	//맵도 향상된 포문
	for(Entry<String,Object> entry : recipe.entrySet()){
		boolean isNew = entry.getKey().equals(menuName); //일치하면 true - 새로 추가된 메뉴라는 뜻
	%>
	<li <%=isNew ? "'id='new-name'":""%>><%=entry.getKey() %> : <%=entry.getValue() %></li>
	
	<%
	}
	
	
%>
	</ul>


<form method="post">
	<input type="text" name="key"/>
	<input type="text" name="value"/>
	<button type="submit" class="btn btn-primary">전송</button>
</form>