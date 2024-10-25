<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body data-context-path="<%=request.getContextPath()%>">



<select name="saveFile">
	
</select>

<script type="text/javascript">

	const fnInit =async()=>{
		//이 스크립트를 호출한 곳을 이용 가는
		const contextPath = document.body.dataset.contextPath
		
		const select=document.querySelector('[name="saveFile"]')
		
		let resp = await fetch(`\${contextPath}/multipart/fileList`,{
				headers:{
					"accept":"application/json"
				}
			})
		
		if(resp.ok){
			let list=await resp.json();
			select.innerHTML=list.map(n=>`<option >\${n}</option>`).join("\n");
		}
		
	}
document.addEventListener("DOMContentLoaded",fnInit)



</script>
</body>
</html>