<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.calc.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="calForm" action="" method="post">
	<input type="text" name="left" placeholder="좌향피연산자" required>
	<select name="operator" required>
	<option value>연산자</option>
	<%=
		Arrays.stream(OperatorType.values())
			  .map(o->String.format("<option value='%s' >%c</option>",o.name(),o.getSign()))
			  .collect(Collectors.joining("\n"))
	%>
	</select>
	<input type="text" name="right" placeholder="우향피연산자" required>
<button type="submit">전송</button>
</form>
<div id="result-area"></div>
<script type="text/javascript">

	const calForm=document.calForm;
	const resultArea=document.getElementById("result-area");
	calForm.addEventListener("submit", async (e)=>{
		e.preventDefault;
		
		let resp = await fetch("",{
			method:"post",
			headers:{
// 				'content-type':"multipart/form-data",
				accept:'application/json'
			},body: new FormData(calForm)
		});
		let calVo= await resp.json();
		resultArea.innerHTML = calVo.expression;
	});

</script>
</body>
</html>