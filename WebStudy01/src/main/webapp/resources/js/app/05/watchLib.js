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

//부모가 가진 생성자를 그대로 지님
class ServerWatch extends ClientWatch{
	constructor(area,url){//재정의 작업
		super(area) //부모의 con를 가져오고 url을 추가하는 재정의 작업
		if(!url){
			throw new Error("할당된 서버 url이 없음")
		}
		this.url=url;
	}
	
	startWatch(){//interval 가동
		this.intervalId =setInterval( async () => {
			let resp=await fetch(this.url);
			let timeText = await resp.text();
			this.area.innerHTML=timeText;
			}, 1000);
		}
	
}


document.addEventListener("DOMContentLoaded",()=>{
	//집합 객체 노드리스트
	let nodeList = document.querySelectorAll(".has-watch");
	nodeList.forEach(area=>{
		if(area.dataset.wlUrl){
			new ServerWatch(area,area.dataset.wlUrl);
		}else{
			new ClientWatch(area); //
		}
	})

})