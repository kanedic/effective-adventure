package kr.or.ddit.props.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.exception.PersonNotFoundException;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;

@WebServlet("/props/personDetail.do")
public class PersonDetailController extends HttpServlet {

	private PersonService service = PersonServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String qsId = req.getParameter("who");
		
		if (StringUtils.isBlank(qsId)) {
			resp.sendError(400, "아이디 누락");
			return;
		}
		try {
			PersonVO person = service.readPerson(qsId);
			req.getSession().setAttribute("personVo", person);
			// WebStudy01/src/main/webapp/WEB-INF/views/props/personDetail.jsp
			req.getRequestDispatcher("/WEB-INF/views/props/personDetail.jsp").forward(req, resp);
		} catch (PersonNotFoundException e) {
			resp.sendError(404, e.getMessage());			
		}
	}
}











