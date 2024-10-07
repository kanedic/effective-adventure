/**
 * 
 */

 const zone= document.getElementById("zone-area");

 //html 렌더링이 끝난 후 추가적인 요청을 위해 DOM을 작성
 document.addEventListener("DOMContentLoaded",()=>{
	 //webstudy01까지
//	 fetch("../../../csr/case2")
//	 .then((resp)=>resp.json())
//	 .then((json)=>{
//		 zone.innerHTML=json.tzId
//		 console.log(json);
//	 })
//	 .catch((err)=>console.log(err))

/*
 js파일의 경로는 이 js파일을 호출하는 html/jsp 파일을 기준으로 잡는다.
 case2.html 기준 - html폴더 상위 resources 상위 webapp에서 /csr/case2라는 서블릿에 요청을 한다.
 만약 이 요청이 성공적으로 처리되면 resp라는 변수로 할당이 되며 resp.ok를 사용해 요청이 성공적인지 확인한다.
 resp.ok가 값이 있으면 true로 resp에 저장된 json객체를 꺼낸다.
json객체를 지정하면 그 안에 tzId라는 key값으로 저장된 value를 꺼내 zone 부분에 넣는다.
오류가 나오면 콘솔로 출력한다.

*/
	 fetch("../../../csr/case2")
	 .then(resp=>{
		 if(resp.ok){
			 return resp.json()
		 }else{
			throw new Error(`에러발생 ${resp.status}`);	 
			  } 
	 }).then(({tzId})=>zone.innerHTML=tzId)
//	 .catch((err)=>console.log(err));
	 .catch(console.log);
	 
	 
	 
	 
 })
 