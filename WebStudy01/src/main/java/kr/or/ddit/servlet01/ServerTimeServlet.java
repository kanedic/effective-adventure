package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now1.do")
public class ServerTimeServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	ZonedDateTime now = ZonedDateTime.now();
		
	StringBuffer html = new StringBuffer();
	
	html.append("<html> ");
	html.append("<body> ");
	html.append("현재 서버의 시간 : "+now.toString());
	html.append("</body>");
	html.append("</html>");
	
	resp.setContentType("text/html;charset=utf-8");
	
	PrintWriter out = resp.getWriter();
	
	//data : 가공하기 전의 날것의 자료들 				 = DAO의 결과물
	//information : data를 가공해서 만든 로직의 결과물	 = Service의 역할
	//  => 프로세싱 과정
	//content : 클라이언트의 상황에 맞게 표현된 메시지	 = Controller의 역할
	
	out.print(html);
		
		
	}
}
