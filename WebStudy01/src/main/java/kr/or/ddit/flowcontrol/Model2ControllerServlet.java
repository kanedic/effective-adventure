package kr.or.ddit.flowcontrol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 컨트롤러의 작업 단계
 * 1.요청을 접수 , 분석 → line , headers , body의 데이터 분석
 * 2.model 생성 (Information)
 * 3.model 전달 (setAttribute, 저장)
 * 4.view를 선택하고 , 이동(forward / redirect)
 * 
 * view의 작업 단계
 * 1. 저장된 model확보 (getAttribute , down casting)
 * 2. UI(컨텐츠 생성)
 */


@WebServlet("/flowcontrol")
public class Model2ControllerServlet extends HttpServlet{
	private Map<String, Object> recipe = new HashMap<>();
	{//코드 블럭 생성자 다음에 실행 생성자 -> 코드블럭 -> init 순으로 생성
		recipe.put("jang","民");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("model", recipe);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/flowcontorl/mainLayout.jsp");
		rd.forward(req, resp);
//		resp.sendRedirect(req.getContextPath()+"/WEB-INF/views/flowcontorl/mainLayout.jsp"); 이건 안됨
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String name =  req.getParameter("key");
		String text =  req.getParameter("value");
		boolean valid = true;
		Map<String, String> errors=new HashMap<>();
		
		if(name==null||name.trim().isEmpty()) {
			valid = false;
			errors.put("key","레시피 네임 누락");
		}
		if(text==null||text.trim().isEmpty()) {
			valid = false;			
			errors.put("value","레시피 설명 누락");
		}
		
		
		if(valid) {
			HttpSession session = req.getSession();
			session.setAttribute("new-menu", name);
			recipe.put(name,text);
			//Post - Redirct - Get 패턴
			resp.sendRedirect(req.getContextPath()+"/flowcontrol");
			
		}else {			
			resp.sendError(400,errors.toString());
		}
	}
}























