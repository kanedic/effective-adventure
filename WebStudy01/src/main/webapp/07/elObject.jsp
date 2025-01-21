<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.calc.OperatorType"%>
<%@page import="kr.or.ddit.calc.CalculateVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4> el에서 객체 사용 방법</h4>

<pre>
	<%
		CalculateVO cal = new CalculateVO();
		cal.setLeft(1);
		cal.setRight(5);
		cal.setOperator(OperatorType.PLUS);	
		request.setAttribute("calAttr", cal);
	%>
	<%=cal.getLeft()+""+cal.getOperator().getSign() +""+cal.getRight() %> = <%=cal.getResult() %> 
	
	${calAttr.left } ${calAttr.operator.sign } ${calAttr.right } = ${calAttr.result }  
	
	<%=cal.getExpression() %>
	${calAttr.expression } 
	${calAttr['expression'] } 
	
</pre>

<hr>
<pre>
	<%
		List<String> list = Arrays.asList("listValue1","listValue2");
		String[] array = new String[]{"arrayValue1","arrayValue2"}; //순차집합 el에서는 리스트와 배열이 똑같은
		
		request.setAttribute("list", list);
		request.setAttribute("array", array);
		
		Set<String> set = new HashSet<>();
		set.add("setValue1");
		set.add("setValue2");
		set.add("setValue2");
		request.setAttribute("set", set);
		
		Map<String,Object> map = new LinkedHashMap();
		map.put("key1","mapValue1");
		map.put("key2","mapValue2");
		map.put("key-3","mapValue3");
		map.put("key 4","mapValue4");
		request.setAttribute("map", map);
		
	%>
	
	${list[3]},${array[3] }
	${set }
	${map.get("key2") }	${map["key2"] } ${map.key2 }
	${map.get("key-3") }	${map["key-3"] } ${map.key-3 }
	\${map.get("key 4") }	${map["key 4"] } \${map.key 4 }

</pre>
<hr>
<pre>

	el에서 지원하는 기본객체 11개
	1.scope 객체 : request / session / application / page
	2. pageContext 
		${pageContext.request.contextPath }${pageContext.servletContext.contextPath }
		${pageContext.session.id }${pageContext.session.creationTime }
	
	6. 요청 파라미터 객체 : param , paramvalues
	7, 요청 헤더 객체 :  headers , headersValues
	8. 쿠키 객체 :cookie
	9. 컨텍스트 파라미터 객체 : initParam
</pre>

</body>
</html>


























