package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//filter도 servlet과 유사하게 어노테이션이 존재하지만 사용하지 않는다.
//이유는 필터가 여러개가 존재 할 시 어노테이션으로는 필터의 순서를 결정할 수 없기 때문.
public class CharacterEncodingFilter implements Filter{
	private String encoding; 

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = Optional.ofNullable(filterConfig.getInitParameter("encoding"))
						   .orElse("utf-8");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//request pre filter 요청 필터
		request.setCharacterEncoding(encoding);

		chain.doFilter(request, response);
		//래퍼 리퀘스트로 없던 프린시펄을 생성해서 
		//response post filter 응답 필터
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
