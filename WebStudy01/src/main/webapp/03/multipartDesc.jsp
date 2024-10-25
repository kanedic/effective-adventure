<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>multipartDesc</title>
</head>
<body>

<h3> 멀티파트 컨텐츠</h3>
<pre>
	MIME : multipart/* - 한번의 전송으로 여러 종류의 컨텐츠를 전송해야 하는 경우,
							body 영역을 여러개의 부분집합으로 분리하여 전송하는 구조
					ex) multipart/form-data [body가 form 안의 name태그의 갯수만큼 쪼개짐]
					ex) multipart/mixed 	[한 번의 전송으로 영상과 이미지를 동시 전송]
			
	멀티파트의 요청을 제대로 처리하기 위해서의 역할		
		1.Front-End
			└1) 동기 요청
				-form ui 구성 (method="post" , enctype="multipart/form-data")
					:form 내부의 name(part name)속성을 가진 입력태그로 body의 part가 분리됨을 뜻
			└2) 비동기 요청
				- fetch 함수 사용
					(method="post")
					(body=new FormData(form))
					(content-type 헤더를 생략하면 boundary속성을 가진 multipart/form-data 헤더가 자동으로 설정 된다.)
			
			==== 전송되는 multipart는 각 파트가 도립적인 헤더(Content-Disposition)를 가지고, name 속성에 의해 식별성이 부여된다.====
					
		2.Back-End
			└1) post 요청 핸들링
			 2) Part API 사용
			 3) @MultipartConfig 설정 필요	
			 4) 문자기반 파트 : parameter 로 핸들링
			 	파일기반 파트 : 업로드 받고, 영추 저장소에 저장하기 위한 처리 
					
</pre>

<form action="<%=request.getContextPath() %>/multipart/sendFileAndText" method="post" 
		enctype="multipart/form-data" id="fileForm">
	<input type="text" name="param1">
	<select  name="param2">
		<option label="TEXT1" value="VAL1">
		<option label="TEXT2" value="VAL2">
	</select>
	<input type="number" name="param3">
	<input type="file"name="uploadFile">
	<button type="submit">全送</button>
	<input type="checkbox">
</form>

<form id="delForm" action="<%=request.getContextPath() %>/multipart/deleteFile">
<div id="result-area">	
</div>
	<input type="file" name="newFile">
	<input type="submit" value="전송버튼">
	<input type="button" value="delete" id="dbtn" name="delEvent" onclick="this.form.requestSubmit()">

</form>
<script type="text/javascript">
//1. 체크박스 값 가져오기
//2. 체그박스의 파일 이름값만 담아서 delete 이벤트 전송
//3  삭제가 완료되면 파일 리스트 갱신 재호출

document.addEventListener("DOMContentLoaded", async()=>{
	const fileForm = document.getElementById("fileForm");
	const resultArea = document.getElementById("result-area");
	const delbtn = document.getElementsByName("delEvent")[0];
	
	
	const fnListup = list=>{
		let href="<%=request.getContextPath()%>/multipart/download.do?filename=";
// 		resultArea.innerHTML=list.map(n=>`<p><a href="\${href+n}">\${n}</a></p>`)
		resultArea.innerHTML=list.map(n=>`<input type="checkbox" class="deleteCheck" value="\${n}"><a href="\${href+n}">\${n}</a><br>`)
								 .join("\n")
		}
	
	
	let resp = await fetch("<%=request.getContextPath()%>/multipart/fileList")
	
	let list = await resp.json();
	fnListup(list);
	
	fileForm.addEventListener("submit",(e)=>{
		e.preventDefault();
		const form = e.target;
		const url = form.action;
		const method = "post";
		const headers = {
				"accept":"application/json",
				//멀티파트로 데이터타입을 정할때는 생략을 해야한다. 구분 문자를 자동으로 만들기 위해
		};
		let body = new FormData(form)
		
		fetch(url,{
			method:method,
			headers:headers,
			body:body
		}).then(resp=>resp.json())
		.then(fnListup)
// 		.then(list=>resultArea.innerHTML=list.map(n=>`<p><a href="javascript:void(0)" onclick="down(this)" data-value="\${n}">\${n}</a></p>`).join("\n"))
		.catch(console.error)
		.finally(()=>{
		//요청처리에 성공이던 실패던 입력된 데이터는 지우기
			fileForm.reset()
		})
		
	});

	delForm.addEventListener("submit",(e)=>{
		e.preventDefault();
		let delList = document.querySelectorAll(".deleteCheck");
		console.log(delList)
		
		const arr = []
		//여기서 체크된 값들을 택함
		for(let i =0;i<delList.length;i++){
			if(delList[i].checked){
				console.log(delList[i].value)
				arr.push(delList[i].value);
			}
		}
		
		let delUrl=e.target.action;
		
		console.log(delUrl)
		console.log(JSON.stringify(arr))
		 
		
		fetch(delUrl,{
			method:'post',
			headers:{
				'Content-Type': 'application/json'
		    },
			body:JSON.stringify(arr)
		})
		.then(res=>console.log(res.ok))
		
		
	})
	
});



//이거 안씀
function down(){
	console.log(123)

	
	
}

</script>
</body>
</html>












