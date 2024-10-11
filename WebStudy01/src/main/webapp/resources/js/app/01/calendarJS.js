const select = document.querySelector("#calendar");
const monthInput = document.getElementById('monthInput');
const minusMonth = document.getElementById('minusMonth');
const plusMonth = document.getElementById('plusMonth');
const serach = document.getElementById('serach');

let inputDateValueFormat =""

function getCalendar(year, month){
	fetch("../getCalendar?year="+year+"&month="+month)
	.then(res=>res.json())
	.then(jsonObj=>createCalendar(jsonObj))
	.catch(new Error('error'))
}

function createCalendar(jsonObj) {
	let lastDate = parseInt(jsonObj.lastDate);
	let firstDayOfWeek = parseInt(jsonObj.firstDayOfWeek);
	let lastDayOfWeek = parseInt(jsonObj.lastDayOfWeek);

	code = "<tr><td>sun</td><td>mon</td><td>tue</td><td>wed</td><td>thu</td><td>fri</td><td>sat</td></tr>"
	
	code += "<tr>";
	if(firstDayOfWeek!=7) {
		for(let i=0; i<firstDayOfWeek;i++){
			code+="<td></td>";
		}
	}
	
	for (let date = 1; date <= lastDate; date++) {
        code += `<td>${date}</td>`;
        
        // 주의 마지막 날이면 새로운 행을 시작
        if ((firstDayOfWeek + date) % 7 === 0) {
            code += "</tr><tr>"; // 현재 행을 닫고 새로운 행 시작
        }
    }
    
    
    let lastDayOfWeekLength = 7-lastDayOfWeek-1;
    for(let last = 0; last<lastDayOfWeekLength; last++){
		code+="<td></td>"
	}
	
	code+="</tr>"
	
	select.innerHTML = code
}



document.addEventListener("DOMContentLoaded",()=>{
	const currentDate = new Date();
	let currentYear = currentDate.getFullYear()
	let currentMonth = String(currentDate.getMonth()+1).padStart(2, '0'); // 1을 더하고 두 자리로 포맷
	inputDateValueFormat = `${currentYear}-${currentMonth}`
    monthInput.value = inputDateValueFormat;
    
	getCalendar(currentYear,currentMonth);
	
	
	minusMonth.addEventListener('click', function() {
		// 현재 input type month의 value 가지고 오기
		const value = monthInput.value;
		// 그거를 date 객체로 만들기
		const date = new Date(value + '-01');
		// 한 달 빼
		date.setMonth(date.getMonth() - 1);
		const year = date.getFullYear();
   		const month = String(date.getMonth() + 1).padStart(2, '0');
   		
    	monthInput.value = `${year}-${month}`;
    	
    	getCalendar(year,month)
	});
	
	plusMonth.addEventListener('click', function() {
		// 현재 input type month의 value 가지고 오기
		const value = monthInput.value;
		// 그거를 date 객체로 만들기
		const date = new Date(value + '-01');
		// 한 달 빼
		date.setMonth(date.getMonth() + 1);
		const year = date.getFullYear();
   		const month = String(date.getMonth() + 1).padStart(2, '0');
   		
    	monthInput.value = `${year}-${month}`;
    	
    	getCalendar(year,month)
	});
	
	serach.addEventListener('click', function() {
		const value = monthInput.value;
		const[year, month] = value.split('-');
		getCalendar(year,month)
	})
	
	
})

