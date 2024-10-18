package kr.or.ddit.servlet07;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request/parameters")
public class FormDataProcessServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		System.out.printf("현재 요청 메소드 : %s\n",req.getMethod());
		
//		getParametersCase1(req, resp);
//		getParametersCase2(req);
//		getParametersCase3(req);
//		getParametersCase4(req);
//		getParametersCase5(req);
		getParametersCase7(req);
		
	}

	/**
	 * getParameterMap 만을 활용하여 파라미터를 얻는 메소드
	 * @param req
	 */
	private void getParametersCase7(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		//출력하기
		//맵의 entry 객체들을 Set<Entry<String,String[]>형태로 변환-> 이를 배열형태로 변환
		parameterMap.entrySet()
					.stream()
					//stream의 결과 배열의 인자 하나씩을 배열 k로 설정 출력
					.forEach(k->System.out.println(Arrays.toString(k.getValue())));
		
	}
	/**
	 * getParameterMap 만을 활용하여 파라미터를 얻는 메소드
	 * @param req
	 */
	private void getParametersCase6(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		//case 5 를 람다형식으로
		//맵의 key 와 value
		parameterMap.forEach((k,v)->System.out.printf(pattern,k,Arrays.toString(v)));
		
	}
	/**
	 * getParameterMap 만을 활용하여 파라미터를 얻는 메소드
	 * @param req
	 */
	private void getParametersCase5(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		//3번방법 엘리먼트 하나로 구성 - 맵의 키 밸류를 한 쌍의 데이터로 하나의 요소 - 한 쌍을 엔트리라고 함

		for(Entry<String, String[]> entry :parameterMap.entrySet()) {
			String name = entry.getKey();
			String[] vals = entry.getValue();
			System.out.printf(pattern,name,Arrays.toString(vals));
		}
		
	}
	/**
	 * getParameterMap 만을 활용하여 파라미터를 얻는 메소드
	 * @param req
	 */
	private void getParametersCase4(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		//2번방법
		
		for(String name : parameterMap.keySet()) {
			String[] vals=req.getParameterValues(name);	
			System.out.printf(pattern,name,Arrays.toString(vals));
		}
		
	}
	
	/**
	 * getParameterMap 만을 활용하여 파라미터를 얻는 메소드
	 * @param req
	 */
	private void getParametersCase3(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		//3가지의 순회방법
		
		Iterator<String> parameterNames = parameterMap.keySet().iterator();
		while (parameterNames.hasNext()) {
			String name = (String) parameterNames.next();
			String[] vals=req.getParameterValues(name);	
			System.out.printf(pattern,name,Arrays.toString(vals));
		}
	}

	/**
	 * getParameterNames 와 getParameter, getParameterValues 를 조합하여 파라미터를 얻는 메소드
	 * @param req
	 */
	private void getParametersCase2(HttpServletRequest req) {
		//모두 문자로 그냥 받고 파싱도 필요없고 그냥 출력만
		//중복데이터 잡기	
		
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			String param=names.nextElement();
//			System.out.println(param);
			
			if(req.getParameterValues(param).length>1) {
				String[] vals=req.getParameterValues(param);	
				System.out.println(Arrays.toString(vals));
			}else {
				String value=req.getParameter(param);
				System.out.println(value);				
			}
			
			
//			String[] vals=req.getParameterValues(param);	
//			System.out.printf(pattern,names,Arrays.toString(vals);
			
			
		}
		
	}

	private String pattern="%s : %s\n";
	
	/**
	 * getParameter, getParameterValues 를 활용하여 파라미터를 얻는 메소드
	 * @param req
	 * @param rsep TODO
	 * @throws IOException 
	 */
	private void getParametersCase1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			
//모든 파라미터는 필수파라미터 - 예외는 동일
		
		//필수 파라미터
		int param1 = Optional.ofNullable(req.getParameter("param1"))
							 .filter(p1->p1.matches("\\d{1,4}"))
							 .map(Integer::parseInt)
							 .orElseThrow(()->new IllegalArgumentException("필수 파라미터 누락 혹은 잘못된 데이터 입력"));
	
		
//		.filter(p1->p1.matches("\\d{1,4}\\-\\d{1,2}\\-\\d{1,2}"))
//		.map(p2->LocalDate.parse(p2))
		LocalDate param2 = Optional.ofNullable(req.getParameter("param2"))
								   .filter(p1->p1.matches("\\d{4}\\-\\d{2}\\-\\d{2}"))
								   .map(LocalDate::parse)
								   .orElseThrow(()->new IllegalArgumentException("필수 파라미터 누락 혹은 잘못된 데이터 입력"));
		
		System.out.printf(pattern,"param2",param2);		

		LocalDateTime param3 = Optional.ofNullable(req.getParameter("param3"))
								       .map(LocalDateTime::parse)
									   .orElseThrow(()->new IllegalArgumentException("필수 파라미터 누락 혹은 잘못된 데이터 입력"));
		
		System.out.printf(pattern,"param3",param3);		
	

		String param4 = Optional.of(req.getParameter("param4"))
								.get();//그대로 값을 얻음 만약 없으면 예외를 발생
		
		System.out.printf(pattern,"param4",param4);
		
		//아무것도 안해도 오류를 발생시켰으면 할때 of -> 만약 null이면 널포익발생
		String[] param5 = Optional.of(req.getParameterValues("param5")).get();
		
		System.out.printf(pattern,"param5",Arrays.toString(param5));
		
		String param6 = Optional.of(req.getParameter("param6")).get();
		
		System.out.printf(pattern,"param6",param6);
		
		String param7 = Optional.of(req.getParameter("param7")).get();
		
		System.out.printf(pattern,"param7",param7);
		
		String[] param8 = Optional.of(req.getParameterValues("param8")).get();
		
		System.out.printf(pattern,"param8",Arrays.toString(param8));
		
		//run 예외의 단계를 한 단계 낮춤 Exception->runtime
		} catch (RuntimeException e) { //try end	
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
}
