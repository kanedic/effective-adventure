package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Decorating Filter Pattern : 어플리케이션에 대한 부가 기능들을 구현할 때 사용됨.
 * 요청과 응답에 대한 전후처리자
 * request는 전[前] 처리자 response는 후[後] 처리자에 위치하게 된다
 * 
 * 1.Filter 구현체 정의
 * 2.컨테이너에 등록
 * 3.요청과의 매핑 설정
 *
 *
 */
public class DummyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.printf("%s 필터 초기화 \n",this.getClass());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//request pre filter 요청 필터
		
		System.out.println("────────────────────request pre filter────────────────────");
		
		chain.doFilter(request, response);//------------

		System.out.println("────────────────────response post filter────────────────────");
		
		//response post filter 응답 필터
	}

	@Override
	public void destroy() {
		System.out.printf("%s 소멸 \n",this.getClass());
		
	}

}




















