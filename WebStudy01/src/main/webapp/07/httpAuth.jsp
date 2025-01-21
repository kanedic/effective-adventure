<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3> Http 인증 시스템</h3>
<pre>
	보안[AuthSystem]의 2대 요소
	인증(Authentication) + 인가(Authorization)
	
	인증[認證] : 사용자가 본인이 맞는지 확인하는 신원 확인 작업
	인가[認可] : 인증된 사용자를 대상으로 보호자원에 대한 접근 권한을 소유했는지 확인하는 작업
	
	JAAS (Java Authentication Authorization Services) : 자바로 구현된 인증/인가 기반의 보안 처리 프레임워크
	1FA = 아이디 + 인증요소 , 2FA 아이디 + 인증요소 [지문, 토큰, 비번,...]
	웹에서 기본 인증 시스템으로 id/password 기반의 1FA를 사용함 
	
	Principal : 하나의 유저를 식별할 수 있는 식별 정보를 가진 객체 ex) ID(username)
				<%=request.getUserPrincipal() %>
				
	Credential : 본임임을 증명할 수 있는 크리티컬 데이터들. ex)비밀번호, 지문, 홍채 ,얼굴,.....
	
	Secured Resource : 접근 제어를 통해 보호가 필요한 자원들

	Role : 인증된 사용자에게 부여되는 역할, 보호자원에 대한 접근 권한은 role에 부여됨
			ex) 출석부는 반장만 조회할 수 있다.
	Permission(허가) : 보호 자원에 접근할 수 있는 권한이 확인되었음.
			ex) 박찬주는 반장이다. 찬주는 출석부에 대한 조회가 허가되었음 - Permission이 부여됨
	
	Authority : 보호 자원에 대한 접근 권한 자체. 
		
	
	★★★ 인증 시스템의 종류
	1. 헤더 기반 인증 
		└ 1) BASIC : 브라우저의 기본 인증 UI 사용 방식
			 └ 인증되지 않은 사용자가 보호자원에 접근
			   401 + www-authenticate 헤더 (Basic)
			   기본 로그인 UI 사용
			   요청 헤더로 Authorazation 헤더가 전송됨 
			   		ex) Authorazation : Basic base64(c001:java)
			   
		장점 : 인증 시스템 구현이 쉽고, 가벼우며 서버에 부하가 덜 됨 (세션 관리를 안 해도 됨)
		단점 : 보안에 취약함, 명시적인 로그아웃이 존재하지 않음 
		 2) BAERER : oAuth,OIDC 서비스 등에서 사용되는 토큰 기반의 인증 방식 
					ex) Authorazation : Baerer encrypt(base64(c001:java)) 2중 암호화 구조
			   
	2. 세션 기반 인증 
		└ 1) FORM : j_username, j_password ,j_security check 등으로 WAS(tomket)을 통해 인증
</pre>
</body>
</html>








