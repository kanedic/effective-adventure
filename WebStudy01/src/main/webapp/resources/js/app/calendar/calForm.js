/**
 * 
 */
const makeOption =(parent,associativeArray)=>{
	for(let pn in associativeArray){ //연관배열구조로 내부접근
			   let pv = associativeArray[pn]; //프로퍼티의 밸류
			parent.insertAdjacentHTML("beforeend",`<option value="${pn}">${pv}</option>`)
		}
}

const fnInit =()=>{
	let url="../calendar/ui-data";
	fetch(url,{
		headers:{
			accept:"application/json"
		}
	}).then(resp=>resp.json())
	.then(({months,locales,zones})=>{//	code=""
		
		/**months.forEach((i,v)=>{
			code+=`<option value="${v+1}">${i}</option>`
		})*/
		/**for(let m of months){}*/
		//index로 접근 0~11
		/**for(let idx in months){
			months[idx]
		}*/
		
		//of와 in을 합친 map m= 값 하나 i 순서 하나 each랑 순서 반대임
		calForm.month.innerHTML=months.map((m,i)=>`<option value="${i+1}">${m}</option>`).join("\n")
		
//		insertAdjacentHTML("",html);
		//프로퍼티의 이름 
		//(calForm.locale,locales);
		makeOption(calForm.zone,zones);
//		for(let pn in locales){ //연관배열구조로 내부접근
//			   let pv = locales[pn]; //프로퍼티의 밸류
//			calForm.locale.insertAdjacentHTML("beforeend",`<option value="${pn}">${pv}</option>`)
//		}
//		for(let zn in zones){ //연관배열구조로 내부접근. zones의 id 하나를 zn으로 zones의 이름으로 밸류를 꺼냄.
//			   let zv = zones[zn]; //프로퍼티의 밸류
//			calForm.zone.insertAdjacentHTML("beforeend",`<option value="${zn}">${zv}</option>`)
//		}
		
		const today = new Date();
		calForm.year.value=today.getFullYear();
		calForm.month.value=today.getMonth()+1;
		
		//유저의 사용환경, 시스템 정보를 모두 담고있는 변수 navigator
		calForm.locale.value=navigator.language;
		
		calForm.requestSubmit();
		
//		month.innerHTML=code
		
	})
	.catch(console.error)
	.finally(()=>{console.log("최종 finally 실행")}); // init 끝
	
	//이벤트 핸들러가 비동기로 실행 할 것을 async로 알림
	calForm.addEventListener("change",(e)=>{
		//이곳에서의 e는 input, select 태그들임 절대 부모인 form이 아니
		console.log("=================>",e.target);
		calForm.requestSubmit();
	});
	
	calForm.addEventListener("submit",async(e)=>{
		e.preventDefault();
		const form = e.target;
		let url = form.action;
		let method = form.method;
		let headers ={
			accept:"text/html"
		};
		
		let formData = new FormData(form);
		let queryString = new URLSearchParams(formData).toString();
		console.log(queryString)
		
		url=`${url}?${queryString}`;
		//응답이 올때까지 대기 await promise의 then을 resp로 받음
		try{
			
			let resp=await fetch(url,{
								method:method,
								headers:headers
								});
			if(resp.ok){//전환이 성공할 때 까지 대기
				let calHTML=await resp.text()
				calArea.replaceChildren("");
				calArea.insertAdjacentHTML("afterbegin",calHTML)
			}else{
				throw new Error(`처리 실패 : ${resp.statusText}`);
			}
		}catch(err){
			console.error(err)
		}
			
	});//submit handler 끝
	
	//
	calArea.addEventListener("click",e=>{
		if(!e.target.classList.contains("link-a"))return;
			
		
		let a = e.target;
		calForm.year.value = a.dataset.year
		calForm.month.value= a.dataset['month']
		//이벤트 발생
		calForm.requestSubmit();
		})		
	
}//fnInit 끝
 //랜더링이 끝난 후 이벤트 발생시켜야함

 document.addEventListener("DOMContentLoaded",fnInit);
 

 
 
 
 