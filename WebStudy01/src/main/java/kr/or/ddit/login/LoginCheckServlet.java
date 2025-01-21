package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet{
	
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO inputdata = new MemberVO();
		inputdata.setMemId(req.getParameter("memId"));
		inputdata.setMemPass(req.getParameter("memPass"));
		
		Map<String, String> errors = new HashMap<>();		
		String dest=null;
		HttpSession session =  req.getSession();
		session.setAttribute("errors", errors);
		validate(inputdata,errors);
		
		if(errors.isEmpty()) {
			MemberVO authMember = service.authenticate(inputdata);
			if(authMember!=null) {
				session.setAttribute("authMember", authMember);
				
				dest="/";
			}else {
				session.setAttribute("message", "로그인 실패. 아이디 혹은 비밀번호가 틀림");
				dest="/login/loginForm.jsp";				
			}
		}else {
			dest="/login/loginForm.jsp";
		}
		
		resp.sendRedirect(req.getContextPath()+dest);
	}
//누락 검사
	private void validate(MemberVO inputdata, Map<String, String> errors) {
		if(StringUtils.isBlank(inputdata.getMemId())) {
			errors.put("memId","아이디 누락");
		}
		if(StringUtils.isBlank(inputdata.getMemPass())) {
			errors.put("memPass","비번 누락");
		}
		
	}
	
}














