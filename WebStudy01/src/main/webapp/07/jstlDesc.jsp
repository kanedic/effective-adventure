<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 코어 태그 약칭 : C 태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> JSTL (Jsp Standard Tag Library)</h3>
<pre>
	: 커스텀 태그(Back-end 모듈, java code로 변환됨)들 중 많이 활용되는 것들을 모아놓은 라이브러리
	&lt;prefix:tag-name attribute-name="attribute-value"&gt;
	
	1.jar 파일을 빌드패스에 추가.
	2.태그 라이브러리 로딩 필요 
		: taglib 지시자 (uri="태그 종류 결정" prifix="커스텀 태그의 접두어 결정")
	
	3. core(c) 태그
		1) 데이터 핸들링 : set, remove 
			<c:set var="attr" value="attr_value" scope="page"></c:set><!-- scope = page가 디폴트로 생략됨 -->
			${attr },${requestScope.attr }
			
			<c:set var="attrClone" value="${attr }" scope="request"></c:set><!-- scope = pageScope가 디폴트로 생략됨 -->
			${attrClone },${pageScope.attr }
			<c:remove var="attrClone" scope="page"/>
			${attrClone },${pageScope.attr }
		
		2) 조건문
			- 단일 조건문 : if
			  <c:if test="${3 lt 5 }">
			  		3은 5보다 작
			  </c:if>
			  <c:if test="${3 ge 5 }">
			  		3은 5보다 크거나 거짓말
			  </c:if>
			  
			  <c:set var="dummy" value="   "></c:set>
			  <c:if test="${empty dummy}">
			  		dummy 속성은 읎다
			  </c:if>
			  <c:if test="${not empty dummy}">
			  		dummy 속성은 잇다 (${dummy })
			  </c:if>
			
			- 다중 조건문 : choose when otherwise
			
			 <c:choose>
			 	<c:when test="${empty dummy }">
			  		dummy 속성은 읎다			 	
			 	</c:when>
				<c:otherwise>
			  		dummy 속성은 잇다 (${dummy })			
				</c:otherwise>
			 </c:choose>
			<a href="?sample1=value1&sample2=44"> 현재 페이지를 대상으로 쿼리 스트링 전달.</a>
			<%=request.getParameter("sample1") %>,${param.sample1 }
			
		3) 반복문 : forEach,forTokens
			forEach 
				for(선언절;조건절;증감절)
				<c:forEach var="i" begin="1" end="10" step="1">
					<c:if test="${i%2 eq 1 }"> ${i } 홀수만</c:if>
					${i }번째 반복
				</c:forEach>
				for(선언절 : 집합객체)
				<c:set var="targetList" value='<%=Arrays.asList("value1","value2") %>'></c:set>
				<c:forEach items="${targetList }" var="element" varStatus="lts" >
					${lts.first ? "시작":"" }
					${element } 속성 , index : ${lts.index } ${lts.count }
					${lts.last ? "종료":"" }
				</c:forEach>
				
				forTokens  문장이 들어감
					<c:forTokens items="민재가 방에 들어간다" delims=" " var="token">
						${token }
					</c:forTokens>
					<c:forTokens items="100,200,300,400" delims="," var="number">
						${number*10 }
					</c:forTokens>
	
</pre>
<div>
	1. sample2 파라미터 유무 확인
	2. 없으면 읎다고 출력
	3. 잇으면 파라미터 검증 - 짝수인지 홀수인지

	
	<c:set var="sample2" value="${param.sample2 }"></c:set>
	<c:if test="${empty param.sample2 }">
		파라미터 없음	
	</c:if>
	<c:if test="${not empty param.sample2 }">
		<c:choose>
			<c:when test="${param.sample2 % 2 eq 0 }"> ${sample2 } 는 짝수</c:when>
		</c:choose>
		<c:otherwise>
		${sample2 } 는 홀수
		</c:otherwise>
		
	</c:if>
	
</div>

</body>
</html>















