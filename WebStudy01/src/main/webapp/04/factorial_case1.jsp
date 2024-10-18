<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팩토리얼 연산</title>
</head>
<body>

<%
//팩토
//10!의 결과도출 방법 1 반복문의 형태 
int sum=1;
int ten=10;
for(int i=1;i<=10;i++){
	sum*=i;
}

%>
<%=sum %>

<%!//일반 함수형 팩토리얼 - 파라미터 num값을 받고 모든 계산을 끝낸 후에 값을 반환
int factorial(int num){
	int sum=1;
	for(int i=1;i<=10;i++){
		sum*=i;
	}
	
	return sum;
} %>

<%=factorial(10) %>

<%!//재귀함수형 팩토리얼 아규먼트값 num이 1이 될때까지 reFactorial(자신)을 계속 호출하며 계산

//call by value 
//call by reference구조
int num1=10; //10이 직접저장
//expr이 참조하는 저장위치에 대한 주소가 담겨져있음
StringBuffer expr = new StringBuffer();

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

10! = <%=reFactorial(num1,expr) %>
<%=expr %>
</body>
</html>