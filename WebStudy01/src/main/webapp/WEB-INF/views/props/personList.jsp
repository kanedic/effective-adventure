<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#newPerson{
		background-color: red;
	}
</style>
</head>
<body>
<a href="<c:url value='/props/personInsert.do'/>"> 신규 회원 등록 </a>
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>${newPerson }</th>
			</tr>
		</thead>

		<tbody>
			<c:if test="${not empty list }">
				<c:forEach var="person" items="${list }">
					<c:choose>
						<c:when test="${person eq newPerson }">
							<c:set var="idValue" value="id='new-person'"/>
							<c:remove var="newPerson" scope="session"/> 
							<!-- flash attribute 사용 후 바로 제거하는 방식 -->						
						</c:when>
						<c:otherwise>
							<c:set var="idValue" value=""/>
						</c:otherwise>
					</c:choose>			
					<tr ${idValue }>
						<td>→${person eq newPerson}
						<c:url value="/props/personDetail.do" var="detailUrl">
							<c:param name="who" value="${person.id }"></c:param>
						</c:url>
						<a href="${detailUrl }">${person.name }</a>
						</td>
						<td>${person.gender }</td>
						<td>${person.age }</td>
					</tr>
				</c:forEach>

			</c:if>

			<c:if test="${empty list }">
				<tr>
					<td colspan="3">없음</td>
				</tr>
			</c:if>
		</tbody>
	</table>

</body>
</html>