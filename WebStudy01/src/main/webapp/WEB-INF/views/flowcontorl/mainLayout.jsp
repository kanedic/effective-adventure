<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css"/>
<style type="text/css">
	#new-name:{
		background-color: yellow;
	}
</style>
</head>
<body>

<h3>최종 응답 페이지</h3>
<h3 class="text-primary"><%=request.getParameter("param") %> -- B main</h3>
<div class="container">

	<div id="part1" class="row bg-info-subtle">
		<jsp:include page="/WEB-INF/views/flowcontorl/fragments/part1.jsp"></jsp:include>
	</div>
	<div id="part2" class="row bg-dark-subtle">
		<jsp:include page="/WEB-INF/views/flowcontorl/fragments/part2.jsp"></jsp:include>
	</div>

</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>