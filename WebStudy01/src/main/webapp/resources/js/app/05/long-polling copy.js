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
class ClientWatch{
	constructor(area){
		if(!area){
			throw new Error("시계를 둘 엘리먼트가 없음")
		}
		this.area=area;
		area._watch = this; //watch는 area를 area는 watch를 소유하게됨

		this.startWatch(); // 생성하면 바로 스타트 워치 시작
	}
	startWatch(){
		this.intervalId=setInterval(()=>{
			this.area.innerHTML=new Date();
		},1000)
	}
	stopWatch(){
		clearInterval(this.intervalId);
	}

}


document.addEventListener("DOMContentLoaded",()=>{
	//집합 객체 노드리스트
	let nodeList = document.querySelectorAll(".has-watch");
	nodeList.forEach(area=>{
		new ClientWatch(area); //
	})

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
	
})