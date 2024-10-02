/**
 * 
 */

const clientarea= document.getElementById("clientarea");
const serverarea= document.querySelector("#serverarea");

const controlBtns=document.querySelectorAll(".control");


//async 비동기 함수를 뜻함
//await 함수가 실행되기 전까지 대기

const watchJob = async()=>{
	clientarea.innerHTML = new Date();	
	
	//js파일의 상대경로는 html의 파일경로를 기준으로 해야함
	let resp = await fetch("../../../now2.aa");
	
	if(resp.ok){
		let jsonObj = await resp.json();
		serverarea.innerHTML=jsonObj.now;	
	}else{
		let errMsg=await resp.text();
		serverarea.innerHTML=errMsg;
		
		//연산배열구조 .이 아닌 []로 접근
		controlBtns.forEach(b=>{
			if(b.dataset['role']=="stop")
				b.click();
			})	
	
}

let intervalId=null;

const watchStart = (btn)=>{
	//지금 누른 start버튼에 d-none 이라는 클래스에 대해
	//있으면 제거 없으면 생성.
	
	intervalId = setInterval(watchJob,1000)
}


const watchStop = (btn)=>{
	
	if(intervalId)clearInterval(intervalId)
	
}

//
controlBtns.forEach((btn,index)=>{
//	btn.addEventListener("click",(event)=>{
							//구조분해 문법 event 객체가 지닌 요소들 중의 하나를 가져옴 여기서는 target
	btn.addEventListener("click",({target})=>{
		
//		console.log(event.target === btn) true
		
		let role = target.dataset.role;
		controlBtns.forEach(b=>b.classList.toggle("d-none"));
		if(role=="start"){
			watchStart(target)
		}else if (role=="stop"){
			watchStop(target)
		}
		
		
		
	});
});
}
