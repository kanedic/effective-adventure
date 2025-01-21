package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 * 1. 로그아웃 처리 (post) 
 * 2. 로그아웃이 끝나면 웰컴페이지로 이동
 * 
 * 
 */
@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
	

		
	}
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) { // 최초요청 새로 만들어진 session인 경우 잘못된 경우임
			resp.sendError(400,"최초요청일 수가 없음");
			return;
		}
		session.invalidate();
		//여기서 세션 삭제 - 리다이렉트 - 달력으로 300 전송 - 달력 300 수신 - 리다이렉트 주소 확인 - 주소값으로 get요청
		
		//웰컴페이지
		resp.sendRedirect(req.getContextPath());
		}
}













