package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now2.do")
public class ServerTimeForJsonServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	ZonedDateTime now = ZonedDateTime.now();
	
	String pattern = "\"now\":\"현재 시간 : %s\"";
	
	StringBuffer json = new StringBuffer();
	
	json.append("{");	
	json.append(String.format(pattern, now));
	json.append("}");
	
	resp.setContentType("application/json;charset=utf-8");
	
	PrintWriter out = resp.getWriter();
	
	out.print(json);
	}
}
