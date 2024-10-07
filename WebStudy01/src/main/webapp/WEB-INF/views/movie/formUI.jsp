    <!-- 지시자 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method='get' action='../streaming.hw'>
<select name='movie' onchange='this.form.submit()'>

<%=request.getAttribute("options") %>

</select>
</form>	
</body>
</html>

