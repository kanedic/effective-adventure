<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 캐시 제어 </h3>

 캐싱? : 네트워크 상에서 발생하는 지연시간으로 인한 부하를 줄이기 위하여 최소한의 정책으로 저장하는 데이터
 	
 	캐시 데이터 제어 헤더
 	1. Cache-Contorol (Http 1.1 버전) : 캐싱 데이터의 저장 여부
 	2. Pragma (Http 1.0 버전) : 캐싱 데이터의 저장 여부
 		
 		no-store : 캐시 데이터를 저장하지 않을 때
 		no-cache : 이전에 저장된 캐시 데이터를 바로 사용하지 않고, 확인 후 사용하도록 유도 (CODE 304)
 		private  : 사용자의 로컬에 캐시 데이터를 저장함.
 		public   : 프록시 서버에 캐시 데이터를 저장하고 해당 서버에 사용하는 pc도 사용가능
 		max-age  : 캐시 데이터의 만료 시한 설정 초 단위 설정
 	
 	3. Expires : 캐시 데이터 만료 시한 설정, 70.1.1/00:00 이후의 밀리 세컨드로 만료 시점 설정

	<%//하나의 헤더에 여러 설정값을 추가하고 싶을 때는 addHeader
		response.setHeader("Cache-Contorol", "no-store");
		response.addHeader("Cache-Contorol", "no-cache");
		response.setHeader("Pragma", "no-store");
		response.addHeader("Pragma", "no-cache");
		
		response.setDateHeader("Expires", 0);
	%>

<button id="load-btn">UI 데이터 로드</button>
<script type="text/javascript">

	let btn = document.getElementById("load-btn");
	let url = `<%=request.getContextPath()%>/calendar/ui-data`
	
	btn.addEventListener("click",async ()=>{

// 		fetch(url)
// 		.then(res=>console.log(res.ok))

		let resp = await fetch(url,{
// 			cache:"defualt"// 서버의 설정에 따라감
// 			cache:"no-store"// 저장하지 않겠다
// 			cache:"no-cache"// 이전에 저장된 캐시 데이터를 바로 사용하지 않고, 확인 후 사용
		})
		
		let data = await resp.json()

		console.log(resp.ok)
		console.log(data)
		
		
		
	})

</script>
</body>
</html>













