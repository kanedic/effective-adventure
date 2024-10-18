<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팩토리얼 연산</title>
</head>
<body>

<%!//일반 함수형 팩토리얼 - 파라미터 num값을 받고 모든 계산을 끝낸 후에 값을 반환
int factorial(int num){
	int sum=1;
	for(int i=1;i<=10;i++){
		sum*=i;
	}
	
	return sum;
} %>

<%=factorial(10) %>
<br>
<%!//재귀함수형 팩토리얼 아규먼트값 num이 1이 될때까지 reFactorial(자신)을 계속 호출하며 계산

//call by value 
//call by reference구조
int reFactorial(int num,StringBuffer expr){
	if(num<=0){//정상적인 파라미터가 아닐 때
		throw new IllegalArgumentException("팩토리얼 연산은 양의 정수를 대상으로 합니다");
	}
	
	if(num==1){//종료 조건을 꼭 걸어줘야함
		expr.append(num);
		return 1;
	}else{
		expr.append(num+" * ");
		return num*reFactorial(num-1,expr);	
	}
} %>

<%
	String opParam=request.getParameter("operand");
	if(opParam!=null&&!opParam.trim().isEmpty()){
		
	int num1=Optional.of(opParam)
					 .filter(op->op.matches("\\d+"))
					 .map(Integer::parseInt)
					 .orElseThrow(()-> new IllegalArgumentException("필수 파라밑에 문제 있음"));
	
	//expr이 참조하는 저장위치에 대한 주소가 담겨져있음
	StringBuffer expr = new StringBuffer();

%>

<%=num1 %>! = <%=reFactorial(num1,expr) %>
<%=expr %>
<%
}	
%>
<br>
필수연산자 누락 시 연산 X 폼 태그만
외부에 노출되어선 안됨


<form action="" method="post">
	<input value="<%=opParam %>" type="number" name="operand" required="required" onchange="this.form.requestSubmit()">
</form>
<br>
<br>
<br>

</body>
</html>