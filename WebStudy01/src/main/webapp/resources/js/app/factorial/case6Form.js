/**
 * facForm
 */

 facForm.addEventListener("submit",(e)=>{
	 //서밋 이벤트의 동기 요청을 중단
	 e.preventDefault();
 
 	//request line : url, method
 	const form = e.target
 	let url = form.action;
 	let options={};
 	let method = 'post';
 	options.method=method;
 	
 	//request head : content-type, accept
 	let headers={
		accept:"application/json",
		'content-type' : 'application/json'
	 }
	 
	 options.headers=headers;
	 
	 let formData= new FormData(form);
	 
	 let nativeTarget={
		 //formData.get('operand') 로 나오는 값은 문자 string 값
		 operand : parseInt(formData.get('operand'))
	 }
 	//request body(only post) : 내용 포스트 방식일때
	 options.body=JSON.stringify(nativeTarget);

 	fetch(url,options)//resolve,reject함수로 이후 이벤트에 대한 처리 함수를 미리 예약해야함
 	     .then((resp)=>{
			  if(resp.ok){
				  return resp.json();
			  }else{
				throw new Error(`응답 실패, ${resp.statusText}`);
			  }
		  }).then(({operand,expression,result})=>{
			  code="";
			  code+=`<h1>${operand}! = ${expression} = ${result}</h1>`;
		  
			  resultArea.innerHTML=code
		  
		  })
 			.catch(console.log)
 			
 	
 
 })