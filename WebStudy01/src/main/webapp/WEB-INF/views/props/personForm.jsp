<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="" method="post" enctype="application/x-www-form-urlencoded">
	<input type="text" name="id" placeholder="id" required>
	<input type="text" name="name" placeholder="name" required>
	<input type="radio" name="gender" value="M" checked required> 남
	<input type="radio" name="gender" value="F" required> 여
	<input type="number" name="age" placeholder="age" required>
	<textarea name="address"></textarea>
	
	<button type="submit"> 전송 </button>
</form>

</body>
</html>