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
 	let method = e.target.method;
 	options.method=method;
 	
 	//request head : content-type, accept
 	let headers={
		accept:"text/plain"
	 }
	 
	 let formData= new FormData(form);
	 let queryString = new URLSearchParams(formData).toString();//formData를 쿼리스트링으로 변환 toString
	 
	 options.headers=headers;
 	if(method=="get"){
		 //get방식일때 데이터 전달을 위하여 query String 방식을 이용하기 위해 url을 수정한다
		 url=`${url}?${queryString}`;
		 
	 }else{
	 	//request body(only post) : 내용 포스트 방식일때
		 headers["content-type"] = form.enctype;
		 options.body=queryString;
	 }
 	
 	
 	fetch(url,options)//resolve,reject함수로 이후 이벤트에 대한 처리 함수를 미리 예약해야함
 	     .then((resp)=>{
			  if(resp.ok){
				  return resp.text();
			  }else{
				throw new Error(`응답 실패, ${resp.statusText}`);
			  }
		  }).then(plain=>resultArea.innerHTML=plain)
 			.catch(console.log)
 			
 	
 
 })