<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/jsonAndRest.jsp</title>
</head>
<body>

데이터를 표현하는 방식으로 자바스크립트의 방식을 따라감.
<h4> JSON(JavaScript Object Notation)</h4>

<pre>
	: 경량의 데이터 교환 형식 (메시지 교환을 위해 표현할 수 있는 방식)
	
	: 데이터 표현 방식이 서로 다른 이기종(異機種) 시스템간의 메시지를 교환하기 위해 필요한 공통 메시지 표현 방식 ==> XML , JSON
	
	JSON syntax
		1. value : null, true/false , number,String("") , object ,array
		2. object : { "propname" : value , "propname2" : value2,... }
		3. array : [ value , ....]
	
	JSON은 REST 구조에 활용됨
			└ 네트워크에서 식별가능한 자원을 에시지의 형태로 교환하는 구조에 대한 포괄적인 표현
	
	REST 구성요소
		1. 자원의 식별성 (URI) [명사]
		2. 자원에 대한 행위 (Method / CRUD) [동사]
		3. 자원의 종류를 표현하는 메타데이터의 존재 (Content-type)
		4. 표현 가능한 자원의 상태(데이터) : JSON
		
	RestFul(Rest 스럽다) : Rest 구조의 일반적 특성에 부합하는 시스템에 대한 수식어
		1. Client/Server의 구조
			Front-end(Client) : Javascript (JSON)
				↑
			  JSON 데이터
				↓
			Back-end(Server) : Java (Gson , Jackson)
		
			native data → json(xml) : marshalling → 010100010100(stream) : serialization
			01011011110(stream) → json(xml) : deSerialization → native : unMarshalling
		
		2. 자원에 대한 유일성이 부여된 식별체계(Uniform interface) 
		3. Stateless : session이 아닌 헤더와 토큰 기반의 인증 시스템을 지님. 
		4. Cachable : 필요한 경우 캐싱 구조를 활용할 수 있음 /캐싱? 서버를 자원에서 가져온 후 pc에 저장해둠 추후에 사용시 저장한 데이터 사용
		5. Layered system : 책임을 쪼개는 구조
	
	RestFul URI (=URL)
		ex) /members/new POST = RestFul URI
		ex) /member/memberInsert.do = NO
		ex) /members GET = RestFul URI
		ex) /member/memberList.do = NO
		ex) /members/a001 GET = RestFul URI
		ex) /member/memberView.do?memId=a001 =NO
	
		/members/a001/profile
		Content-Type:image/jpeg
		
		/member/a001/profile.jpg
		
		
		
	</pre>

<script type="text/javascript">
	let nativeTarget = {
			prop1 : "value1",
			prop2 : 23,
			prop3 : false,
			prop4 : {
				innerProp1 : "innerValue"
			},
			prop5 : [1,2,3],
			prop6 : "石"
			
	};
	
	let json = JSON.stringify(nativeTarget);
	
	console.log("원본 : \n",nativeTarget)
	console.log("marshalling 된 json: \n",json)
	
	let unMarshalledObj=JSON.parse(json)
	
	console.log("unMarshalling 된 json: \n",unMarshalledObj)
	
</script>


</body>
</html>