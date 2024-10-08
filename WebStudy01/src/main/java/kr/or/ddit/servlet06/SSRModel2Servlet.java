package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ssr/model2/getHtml")
public class SSRModel2Servlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ZoneId timeZone=ZoneId.systemDefault();
	
		Locale locale = Locale.getDefault();
		
		String tzName=timeZone.getDisplayName(TextStyle.FULL, locale);
	
		String localeName=locale.getDisplayName(locale);
		
	
		req.setAttribute("tzName", tzName);
		req.setAttribute("localeName", localeName);
		
		req.getRequestDispatcher("/WEB-INF/views/ssr/sample.jsp").forward(req, resp);
//		/WebStudy01/src/main/webapp/tmpl/sample.yg
//		/WebStudy01/src/main/webapp/WEB-INF/views/ssr/sample.jsp
	}
}
