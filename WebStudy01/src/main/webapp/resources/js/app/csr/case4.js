/**
 * 
 */

 
 document.addEventListener("DOMContentLoaded",()=>{
	 
	 							//어트리뷰트 셀렉터
//	 const select = document.querySelector("[name='movie']"); name=movie인것들만 다 가져옴
	
	 const select = document.querySelector("[name='movieOrImage']");
	 
	 fetch("../../../movie/csr/ALLfileList")
		  .then(resp=>resp.json())
		  .then(array=>select.innerHTML=array.map(name=>`<option>${name}</option>`)
	 			    							.join("\n"))
	 .catch(console.log)
	 //함수의 레퍼런스
	 
	  const select2 = document.querySelector("[name='image']");
	 
	 fetch("../../../movie/csr/imagefileList")
	 .then(resp=>resp.json())
	 .then(array=>select2.innerHTML=
	 								array.map(name2=>`<option>${name2}</option>`)
	 									 .join("\n"))
	 .catch(console.log)
	 
 })

 
 
 
 