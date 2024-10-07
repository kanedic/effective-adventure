package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 스펙 ( why / what / how )
 * 
 * 사용 목적 : 웹 상의 불특정 다수의 클라이언트가 발생시키는 다양한 요청을 처리하기 위해
 * 				동적 자원 (컨텐츠)를 생성할 수 있는 자바 기반의 백엔드 모듈이 필요함.
 * 
 * 서블릿 이란? : 웹 상의 요청을 받고, 분석하여 그에 적합한 동적 응답을 생성할 수 있는
 * 				 자바 객체(가 만족해야 하는 조건에 대한 명세서) 를 CODE화 
 * 				-> HttpServlet
 * 
 * 서블릿 개발 단계
 *  1. HttpServlet의 확장 클래스 구현. (extends)
 *  2. 필요에 따른 적절한 callback 메소드를 재정의.
 *  	***서블릿 컨테이너의 역할과 특성
 *  		:서블릿의 생명주기 관리자, 요청이 발생하면 해당 서블릿의 특정 콜백 메소드를 실행함.
 *  		1) 서블릿 인스턴스를 생성하는 시점.  : load-on-startup 설정.
 *  			lazy-loading(지연 로딩) : 서버가 구동되는 시점에 인스턴스 아닌, 
 *  										해당 서블릿에 대한 최초의 요청이 발생 했을 때 생성을 함.
 *				eager-loading(즉시 로딩) : 서버가 구도되는 시점에 인스턴스를 미리 생성함.  		
 *  		2) 서블릿은 싱글톤 패턴으로 관리된다. [ 서블릿은 단 하나만 생성됨 ]
 *  		3) 서블릿의 생명주기 관리 과정에서 발생하는 이벤트에서 관련 콜백 메소드를 실행함.
 *  			생명주기 콜백 : init(서블릿 생성 후 초기화 작업 종료 후 호출) , destroy (서블릿 소멸 직전 호출)		
 *  			요청 콜백
 *  			 └service : 요청이 발생하면, 컨테이너가 직접 실행하는 메소드로
 *  						http method를 판단하고 , 그에 맞는 doXXX 계열 메소드를 호출하는 역할.
 *  			 └doXXX : 하나의 http method를 별개로 처리하기 위한 메소드
 *  		4) 서블릿의 인스턴스 생성 시점에 초기화 파라미터를 전달한다.
 *  
 *  3. 서블릿 컨테이너가 서블릿을 관리, 운영 할 수 있는 설정.
 *  	1) 컨테이너에 서블릿 클래스 등록
 *  		2.x version : web.xml(deployment descriptor 배포 서술자)에 
 *  						servlet->servlet-name, servlet-class 등록
 *  		3.x version : @WebServlet 마커 어노테이션
 *  	2) 서블릿 매핑을 통해 컨테이너가 서블릿을 실행 할 수 있는 조건 설정.
 *  		2.X version : web.xml에 servlet-mapping -> servlet-name , url-pattern
 *	 		3.X version : @WebServlet(url) [싱글벨류 어노테이션]
 *  						@WebServlet(url,//) [멀티벨류 어노테이션]
 *  
 *  4. 컨테이너 리로드
 * 
 * *** 서블릿 스펙에서 제공되는 객체
 * 1. HttpServletRequest : http 프로토콜로 발생[패키징]한 요청에 대한 정보를 캡슐화한 객체.
 * 2. HttpServletResponse : http 프로토콜로 발생[패키징]한 응답에 대한 정보를 캡슐화한 객체.
 * 요청이 발생할 때 마다 생성. 
 * 
 * 3. HttpSession : 한 클라이언트가 하나의 에이전트 프로그램[브라우저]으로 형성한 하나의 세션에 대한 정보를 캡슐화한 객체
 * 처음 요청이 발생할 때 생성 로그아웃 할 때 소멸
 * 
 * 4. ServletContext : 하나의 웹어플리케이션(컨텍스트)에 대한 정보를 캡슐화한 객체
 * 						하나의 웹어플리케이션(컨텍스트)에 대해 싱글톤 구조로 생성되는 객체.
 * 서버가 구동될 때 생성 서버가 꺼지면 소멸
 * 
 * @author PC_02
 *
 *레이제 로딩방식
 */
											//url 주소를 여러가지로 잡아서 여러 요청을 해당서블릿에서 처리.
//@WebServlet(value="/desc",name = "dada",urlPatterns = {"/desc","/DESC"})
public class DescriptionServlet extends HttpServlet{

	private String param1Value;
	private ServletContext application;

	//싱글톤과 멀티스레드 구조에서 전역변수를 선언하면 고장 가능성이 높아지기에 사용 금지
//	private ZonedDateTime now;

	public DescriptionServlet() {
		super();					//현재 클래스의 이름얻기
		System.out.printf("%s 객체 생성\n",this.getClass().getName());
	
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		//이 super를 제거하면 얻어야할 정보를 못얻음
		super.init(config);
		application = getServletContext();
		
		System.out.printf("%s 서블릿 초기화 완료 \n",this.getClass().getSimpleName());
		
		param1Value=config.getInitParameter("param1");
		
		if(param1Value!=null && !param1Value.isEmpty() ) {
			System.out.printf("param1 의 값 : %s\n",param1Value);
			
		}
		
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("요청 접수, http request method 판단");
		//이 super에서 요청을 판단하여 내부에서 doget메소드를 실행
		super.service(req, resp);
		System.out.println("요청 처리 완료.");
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.printf("GET 메소드 요청 처리, 실행 쓰레드 명: %s\n",Thread.currentThread().getName());
		ZonedDateTime now = ZonedDateTime.now();			
		
		resp.setContentType("text/plain;charset=utf-8");
		
		resp.getWriter().println(now.toString());
		
		PrintWriter out = resp.getWriter();
		
		out.println(now.toString());
		out.println(application.hashCode());
		out.println(application.getInitParameter("contextParam1"));
		
	}
	
	@Override
	public void destroy() {
		
		System.out.printf("%s 서블릿 소멸\n",this.getClass().getSimpleName());
	}

	
	
	
	
}
