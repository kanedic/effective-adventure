package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Html 페이지의 렌더링 방식.
 * 1. SSR (Server Side Rendering) 방식 : 최종 UI를 구성하는 전체 HTML 소스를 서버에서 생성하여 한번의 응답으로 전송하는 형태 
 * 2. CSR (Client Side Rendering) 방식 : 최초의 요청에 대한 응답으로 html 소스의 일부 템플릿을 전송하고,
 * 										 한 번 이상의 데이터에 대한 응답으로 전송된 데이터를
 * 										  클라이언트 측에서 한번 이상 추가 렌더링을 하는 형태
 * 
 * 백엔드 모듈
 * 요청 읽기 요청 처리 응답 데이터 생성
 * 
 * SSR방식에서 전체 HTML 컨텐츠를 생성하는 책임의 분리 구조.
 * 1.Model1 : 요청 읽기 - 요청 처리 - 응답 데이터생성을 하나의 servlet이나 jsp에서 모두 처리함.(책임 분리가 안됨)
 * 2.Model2 : 요청 읽기 - 요청 처리를 컨트롤러로 표현하는 servlet이 담당하고,
 * 			  응답 데이터를 생성하고 전송하면 view형태의 템플릿 엔진으로 처리하는 구조
 * 
 * 
 * 
 */

@WebServlet("/ssr/case1")
public class ServerSideRenderingServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ZoneId timeZone=ZoneId.systemDefault();
	//두가지 언어가 함께함. ssr의 큰 특징이자 단점 
		
		String infomation = timeZone.toString();
		
		StringBuffer html = new StringBuffer();
		
		html.append("<html>                   ");
		html.append("<body>                   ");
		html.append(String.format("<h4>서버의 시간대 : %s <h4>  ",infomation));
		html.append("</body>                  ");
		html.append("</html>                  ");
		
		resp.setContentType("text/html;charset=utf-8");
		
		try(
			PrintWriter out = resp.getWriter();	
				){
			out.print(html);
		}
		
	}
	
}
