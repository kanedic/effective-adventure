<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
asdasd
<form id="sessionForm">
<div id="timer"> <%=session.getMaxInactiveInterval() %></div>
<a href="javascript:head();">asdasdasdsaasdasd</a>
</form>
<!-- <script type="text/javascript" src="../resources/js/app/05/sessionTimer.js"></script> -->
<script type="text/javascript">
var setTime = <%=session.getMaxInactiveInterval()%>
const url = "../sessionTimer";

	//WebStudy01/src/main/java/kr/or/ddit/login/sessionTimer.java
document.addEventListener("DOMContentLoaded",()=>{
	
	console.log(url)
	
	tim = setInterval(()=>{
		setTime = setTime-1
		var min = parseInt(setTime/60);
		var sec = setTime%60;
		timer.innerHTML=`\${min} : \${sec}`
		
    	  },1000)
	
	
// 	console.log(setTime-2)
})
function head(){
	let goFetch=confirm('시간을 갱신하시겠습니까?')
	
	if(goFetch){
		fetch(url,{
			method:"head"
		}).then(res=>location.href="")
		.catch(err=>location.href="")
	}else{
		console.log("ㅁㄴㅇ")
	}
		
}



</script>
</body>
</html>