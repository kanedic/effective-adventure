/**
 * 
 */

/**
 *  클라이언트의 시계
 * 1. 시계 출력 엘리먼트
 * 2. 시간을 갱신할 수 있는 작업 - (1초마다 갱신)
 * 3. 시간을 멈출 수 있는 기능
 * 4. 멈춘 시간을 다시 흐르게 
*/

/**
 * 서버 시계
 * 1. 시간 출력 엘리먼트
 * 2. 서버의 갱신되는 시간 자원을 반복적으로 출력하기 위한 작업
 * 3. 시계 멈추기
 * 4. 시계 재가동
 */



document.addEventListener("DOMContentLoaded",()=>{
	//집합 객체 노드리스트
	let nodeList = document.querySelectorAll(".has-watch");

	const stopBtn=document.getElementById("stop-btn");
	const resumeBtn=document.getElementById("resume-btn");
	
	//1초마다 시간이 갱신되도록 타임아웃 - ?초 후에 실행되도록이라 안됨
//	clientArea.innerHTML = new Date()
	
	// let watch1 = new ClientWatch(clientArea1);	
	// let watch2 = new ClientWatch(clientArea2);	
	// let watch3 = new ClientWatch(clientArea3);	

	//저장용 배열 생성 - 배열에 인스턴스 저장
	// let arr=[watch1,watch2,watch3]
	// let watchs=[];
	// watchs.push(new ClientWatch(clientArea1),new ClientWatch(clientArea2),new ClientWatch(clientArea3))

	//스톱버튼에서 배열 순회하며 clear 실행할 이벤트 생성
	stopBtn.addEventListener("click",(e)=>{
		
		//배열 arr을 순회함 i는 객체의 정보 즉 객체 하나, v는 밸류 [0,1,2] 
		// arr.forEach((i,v)=>{
		// 	console.log(i);
		// 	console.log(v);dd
		// 	i.stopWatch();
		// })

		//배열의 객체 하나씩을 wat에 할당한다. wat 은 Watch1... 과 동일함. 그러니 wat의 stop을 호출하면 종료
		// for(let wat of arr){
		// 	wat.stopWatch();
		// }

		nodeList.forEach((area)=>{
			area._watch.stopWatch();
		})
		
		// clearInterval(intervalId);
		
		stopBtn.classList.toggle("invisible")
		resumeBtn.classList.toggle("invisible")
	})
	
	// RESUM 버튼을 클릭하면 모든 시계는 다시 흘러간다
	resumeBtn.addEventListener("click",()=>{
		// arr.forEach((i,v)=>{
		// 	i.startWatch();
		// })
		nodeList.forEach((area)=>{
			area._watch.startWatch();
		})
		stopBtn.classList.toggle("invisible")
		resumeBtn.classList.toggle("invisible")
	})
	/**
 * 1.페이지가 로딩이 되면 옵션이 생성되어야함
 * 2.타임존 리스트만을 옵션으로 끝
 * 3.선택하면 data속성의 jsp뒤에 파라미터를 붙혀야함. - 체인지 이벤트
 */

	let url = `../calendar/ui-data`
	const selZone=document.getElementById("zone")
	
	
	fetch(url)
	.then(resp=>resp.json())
	.then(({locales,months,zones})=>{
		for(zon in zones){//셀렉트 태그 끝나는거의			
			value=zones[zon]

			selZone.insertAdjacentHTML("beforeend",`<option value=${zon}>${value}</option>`)
			
		}
	})

	var dataZone;
	nodeList.forEach((i)=>{
		if(i.dataset){
			dataZone=i;
		}
	})
	
	const paramUrl=dataZone.dataset.wlUrl;
	console.log(paramUrl)
	
//	selZone.addEventListener("change",({target})=>{
	selZone.addEventListener("change",(e)=>{
		console.log(e.target.value)
		let nara = e.target.value;
		
		document.querySelectorAll("[data-wl-url]").forEach((area)=>{
			let originUrl = area.dataset.wlUrl;
			area._watch.url = `${originUrl}?zone=${nara}`
			//현재 사용되는시계의 인스턴스
		})

		// let qsurl=`${paramUrl}?zone=${nara}`	
		// console.log(qsurl)
		
		// //데이터를 전송앴으면 div의 data속성을 바꿔서 서버로 전송해야함 전송하면 현재 서버의 시간을 시간에 맞춰서 나오게 설정
		// //1. 데이터를 서버 타임으로 전송함 
		// fetch(qsurl)
		// .then(()=>{
		// 	dataZone.setAttribute('data-wl-url',qsurl)
		
		// })
		
	})
	
})













