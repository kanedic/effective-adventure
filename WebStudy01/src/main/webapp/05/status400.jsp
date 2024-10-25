<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body data-context-path="<%=request.getContextPath()%>">

<button id="btn404"> 404 에러 확인</button>
<button id="btnHead"> head 요청 전송</button>head는 요청만 하고 응답이 필요 없을 때 사용하는 메소드
<button id="btn200_get_json"> get 요청 전송(json)</button>
<button id="btn200_get_html"> get 요청 전송(HTML)</button>
<button id="btn406"> 406 에러 확인</button>
<button id="btn200_post_parameter"> Post parameter 전송</button>
<button id="btn200_post_json"> Post json 전송</button>
<button id="btn415"> Post XML 전송 415 에러 확인</button>
<button id="btn400"> Post json 전송(필수데이터 누락. 400CODE)</button>

<div id="resultArea"></div>
<script type="text/javascript">
	const contextPath = document.body.dataset.contextPath;
	const baseURL=`\${contextPath}/status/send-and-receive`;


//404의 클릭 이벤트 제어
btn404.onclick = async function(){
	let resp = await fetch("/dummyErrorUrl");
	
	let message = await resp.text();
	resultArea.innerHTML=message;
}

btnHead.onclick = async function(){
	let resp = await fetch(baseURL,{
		method:"head"
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn200_get_json.onclick = async function(){
	let resp = await fetch(baseURL,{
		headers:{
		accept:"application/json"			
		}
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn200_get_html.onclick = async function(){
	let resp = await fetch(baseURL);
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn406.onclick = async function(){
	let resp = await fetch(baseURL,{
		headers:{
		accept:"application/xml"			
		}
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn200_post_parameter.onclick = async function(){
	let formData = new FormData(); //키 밸류 쌍을 만드는게 목적 폼이 없으면 만들면 됨
	formData.append("data3","DATA3");
	
	let resp = await fetch(baseURL,{
		method:"post",
		body:new URLSearchParams(formData)	//fetch가 body를 분석한 후 알아서 컨텐츠 타입을 지정함
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn200_post_json.onclick = async function(){
	let nativeTarget={
			"data3":"DATA3"
	}
	
	let resp = await fetch(baseURL,{
		method:"post",
		headers:{
			'content-type':'application/json'
		},
		body:JSON.stringify(nativeTarget)
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn415.onclick = async function(){
	let resp = await fetch(baseURL,{
		method:"post",
		headers:{
			'content-type':'application/xml'
		},
		body:"<root><data3>DATA3<data3></root>"
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

btn400.onclick = async function(){
	let nativeTarget={
			"data33":"DATA3"
	}
	
	let resp = await fetch(baseURL,{
		method:"post",
		headers:{
			'content-type':'application/json'
		},
		body:JSON.stringify(nativeTarget)
	});
	
	let message = await resp.text();
	resultArea.innerHTML=message;	
}

















</script>
</body>
</html>