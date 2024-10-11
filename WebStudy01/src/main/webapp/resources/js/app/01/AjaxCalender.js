/**
 * 
 */

 
 
 
window.onload=function(){
//WebStudy01/src/main/java/kr/or/ddit/servlet06/AjaxCalenderServlet.java
     const monthSelect = document.getElementById("month");
	
	
	fetch("/WebStudy01/AjaxCalenderLoading.do"
			,{method : "post"})
			.then(resp=>resp.json())
			.then(data =>console.log(data) )
		.catch(err=>alert("1"))
	
	fetch("/WebStudy01/AjaxCalenderGetYoil.do"
			,{method : "post"})
			.then(resp=>console.log(resp.json()))
		//	.then(data =>console.log(data) )
		.catch(err=>alert("2"))
	
	
	fetch("/WebStudy01/AjaxCalenderGetMonth.do"
			,{method : "post"})
			.then(resp=>resp.json())
			.then(data => {
            options = ''; 
            data.forEach(item => {
                options += `<option value='${item.getMon}'> ${item.mon} </option>`;
            });
            monthSelect.innerHTML = options;
        })
		.catch(err=>alert(err.status))
		
	
	
	const calForm =	document.calForm;
	calForm.year.value="";
	calForm.month.value="";
	calForm.locale.value="";
	calForm.locale.zone="";
	
	document.querySelectorAll(".link-a").forEach(a=>{
		a.addEventListener("click",e=>{
			calForm.year.value = a.dataset.year
			calForm.month.value= a.dataset['month']
			//이벤트 발생
			calForm.requestSubmit();
		})		
	});
 }