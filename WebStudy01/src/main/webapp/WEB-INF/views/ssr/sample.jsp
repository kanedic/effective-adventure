<%@page contentType="text/html; charset=utf-8" %>
<!-- 스크립플립 -->
<!-- @ 지시자 -->
<!-- = 표현식 -->
<!-- ! 선언부 -->
<html>                
<style type='text/css'>
	.red{
		background-color:red
	}
	.yellow{
		background-color:yellow
	}
</style>
<script  src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<body>                  
<h4 class='red'> 서버 시간대 : ${tzName}</h4>
<h4 class='yellow'> 서버 시간대 > 서버 Locale : ${localeName}</h4>
</body>                
</html>                