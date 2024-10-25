<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	1. model2 요청과 응답의 구분의 책임을 나누어야함. 이 홈페이지에서 이벤트가 발생하면
	요청은 btn서블렛에서 받고 응답으로 b페이지인 bts멤버 페이지가 나옴 ui제공이 /bts에/멤버명임
	근데 그럼 시작을 여기서 하드코딩할게아니라 서블릿 시작으로 여기로 와서 제공해야하는건가 그리고 여기오면
	옵션이 만들어 질거고 그거또뭐냐 페이지 모듈화 구조면 모듈이면은 또 응답의 책임을 나누는 거
	그럼 동기를 어케처리해야하는거지

<form id="btnForm" action="../../../homework/btn" onchange="btnForm.requestSubmit();">
	<select id="sel">
		<option value> 택 1 </option>
	</select>
	<button type="submit">보내기</button>
</form>
<script type="text/javascript">


document.addEventListener("DOMContentLoaded", async (e)=>{
	const actUrl = sel.action
	const selBox = document.getElementById("sel")
	const btnForm = document.getElementById("btnForm")
	console.log(selBox)
	console.log(btnForm.action)
		const resp = await fetch("../../../homework/btn");
        const data = await resp.json();
        console.log(data);
        for (let key in data) {
            let value = data[key];
			// console.log(key)
            // console.log(value);
            selBox.insertAdjacentHTML("beforeend", `<option value=\${key}>\${value}</option>`);
     
		}

	btnForm.addEventListener("submit",async (e)=>{
		e.preventDefault();
		console.log(selBox.value)
		const val = selBox.value;
		
		const url=btnForm.action;
		let memUrl=`\${url}\/\${val}`
		console.log(memUrl)

		const res=await fetch(memUrl)
		const d=await res.text();


	})







	})

</script>
</body>
</html>
