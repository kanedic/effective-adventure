package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/csr/case2")
public class ClientSideRenderingDataServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ZoneId timeZone=ZoneId.systemDefault();
	//두가지 언어가 함께함. ssr의 큰 특징이자 단점 
		
		String infomation = timeZone.toString();
		
		String pattern = "{\"tzId\":\"%s\"}";
		
		
		//html 과 json의 차이 - 꾸민 데이터를 내보낼 것이냐 순수한 데이터만 내보낼 것이냐
		//3가지 응답을 모두 처리해서 하나의 페이지를 완성
		
		 
		resp.setContentType("application/json;charset=utf-8");
		
		try(
			PrintWriter out = resp.getWriter();	
				){
			out.print(String.format(pattern, infomation));
		}
		
	}
}
