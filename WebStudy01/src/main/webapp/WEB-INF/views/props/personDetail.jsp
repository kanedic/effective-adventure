<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#pt{
	border: 1px solid black;
	text-align: center;
	}
</style>
</head>
<body>
${personVo }
<form action="<%=request.getContextPath() %>/props/personUpdate.do" method="post">
<table id="pt" border="1" >
	<thead>
		<tr>
		 <th> &nbsp;계정&nbsp; </th>
		 <th> &nbsp;이름&nbsp; </th>
		 <th> &nbsp;성별&nbsp; </th>
		 <th> &nbsp;연령&nbsp; </th>
		 <th> &nbsp;주소&nbsp; </th>
		</tr>
	</thead>
	<tbody>
		<tr>
		 <td><input required type="text" value="${personVo.id }" name="id" readonly></td>
		 <td><input required type="text" value="${personVo.name }" name="name"></td>
		 <td><input required type="text" value="${personVo.gender }" name="gender"></td>
		 <td><input required type="text" value="${personVo.age }" name="age"></td>
		 <td><input required type="text" value="${personVo.address }" name="address"> </td>
		</tr>
	</tbody>
</table>
<button type="submit">수정전송</button>
<button id="delBtn" type="button" >삭제ㅈ앶앶앵</button>
</form>

<script type="text/javascript">
const url = "<%=request.getContextPath() %>/props/personDelete.do"

document.addEventListener("DOMContentLoaded",()=>{

	delBtn.addEventListener("click",()=>{
		var id =document.getElementsByName("id")[0].value
		
		qUrl=`\${url}?who=\${id}`
		
		console.log(qUrl)
				
		fetch(qUrl)
		//비동기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 동기를
		
	})

})


</script>




</body>
</html>









