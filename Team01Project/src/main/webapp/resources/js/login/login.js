document.addEventListener("DOMContentLoaded", function(){
	 
	 if (exception.value) { // 로그인 오류 발생시 동작
	
  
	//swal 닷메 
	
	}
});

function fnLogin(role) {
    const loginForm = document.forms["loginForm"];
    
    if (role === 'freshman') { // 신입생 처리
        loginForm.id.value = '2024100012'; // 신입생 샘플 ID
        loginForm.pswd.value = 'java'; // 신입생 샘플 비밀번호
    } else {
        loginForm.id.value = `2024${role}00001`; // 다른 사용자 ID 생성 규칙
        loginForm.pswd.value = 'java';
    }

    loginForm.submit(); // 폼 제출
}

