/**
 * 
 */

 
 document.addEventListener("DOMContentLoaded",()=>{
	 
	 							//어트리뷰트 셀렉터
//	 const select = document.querySelector("[name='movie']"); name=movie인것들만 다 가져옴
	
	 const selectAll = document.querySelector("[name='movieOrImage']");
     const selectImage = document.querySelector("[name='image']");
	
	 const forms = document.querySelectorAll("form");
	
	
	 
	 fetch("../../../movie/csr/ALLfileList")
		  .then(resp=>resp.json())
		  /*.then(array=>selectAll.innerHTML=array.map(name=>`<option>${name}</option>`)
	 			    							.join("\n"))*/
	 	  .then(array=>selectAll.insertAdjacentHTML("beforeend",
		  										array.map(name=>`<option>${name}</option>`)
	 			    							.join("\n"))
	 	 )
	 .catch(console.log)
	 //함수의 레퍼런스
	 
	 
//	 fetch("../../../movie/csr/imagefileList")
	 fetch("../../../image/csr/imagefileList")
	 .then(resp=>resp.json())
	 .then(array=>selectImage.innerHTML+=
	 								array.map(name2=>`<option>${name2}</option>`)
	 									 .join("\n"))
	 .catch(console.log)
	 
	 let renderer = {
		 image:(src,parent)=>{
			 parent.innerHTML= `<img src='${src}' />`
		 },
		 movie:(src,parent)=>{
			 parent.innerHTML=`<video src='${src}' autoplay controls />`
		 },
		 
	 }
	 
	 
	 //모든 폼태그에 이벤트를 위해 반복문 사용 e는 이벤트 f는 폼태그 하나
	 forms.forEach((f)=>{
		 f.addEventListener("submit",(e)=>{
			 //이벤트 액션을 중지 - 페이지가 전환되지 않음
			 e.preventDefault();
			 
//			 e.target 과 f는 같음
			 let action = e.target.action;
			 
			 //폼태그에 대한 formdata생성
			 let formData = new FormData(f);
			 let queryString = new URLSearchParams(formData)
			 let src = `${action}?${queryString}`;
			 
			//http://localhost/WebStudy01/movieOrImage.hw?movieOrImage=sample-mp4-file.mp4
		 	//http://localhost/WebStudy01/image/streaming.hw?image=cute5.JPG
		 
		 //같은 코드
//		 	f.dataset.role
		 	let role = f.dataset['role'];
		 	let targetArea = f.dataset['targetArea'];
		 	
		 	let parent = document.querySelector(targetArea);
		 
		 	
		 	console.log(targetArea)
		 	renderer[role](src,parent);
		 	
		 	
		 
		 
		 });
	 });
	 
	 
	 
 })

 
 
 
 