package kr.or.ddit.props.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;

@WebServlet("/props/personDelete.do")
public class PersonDeleteController extends HttpServlet{
	
	private PersonService service = PersonServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("who");
		
		System.out.println(id);
	
		service.removePerson(id);
		
		//삭제하면 다시 리스트로 
		resp.sendRedirect(req.getContextPath()+"/props/personList.do");
///WebStudy01/src/main/webapp/WEB-INF/views/props/personList.jsp
	}
}
