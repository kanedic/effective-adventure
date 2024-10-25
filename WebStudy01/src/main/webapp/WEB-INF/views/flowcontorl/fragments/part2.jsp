<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>PART2 에서 만든 컨텐츠 으으으으이ㅡ으으으의으으응ㅇㅇㅇㅇ</h1>
<h3 class="text-info-emphasis"><%=request.getParameter("param") %> -- D part2</h3>
<h3 class="text-info-emphasis"><%=request.getAttribute("model") %> -- D part2</h3>

<form method="post">
	<input type="text" name="key"/>
	<input type="text" name="value"/>
	<button type="submit" class="btn btn-primary">전송</button>
</form>