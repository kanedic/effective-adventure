package kr.or.ddit.props.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;

/**
 * 다건조회 : /props/personList.do(GET)
 * 단건조회 : /props/personDetail.do?who=A0011(GET)
 * 등록	  : /props/personInsert.do(GET,POST)
 * 수정	  : /props/personUpdate.do?who=A0011(GET,POST)
 * 삭제	  : /props/personDelete.do?who=A0011(GET)
 *  
 *  HCLC (High Cohesion Loose Coupling) 응집력을 높히고 결합력을 낮춰라
 *  //안나오는 이유는 서비스와 다오를 [그냥] 생성하고있음. 
 *  
 *  
 */
@WebServlet("/props/personList.do")
public class PersonListController extends HttpServlet{
	
	private PersonService service = PersonServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("───────────── request servlet process ─────────────────────");
		
		List<PersonVO> personList = service.readPersonList();
		
		req.setAttribute("list", personList);
		
		String view ="/WEB-INF/views/props/personList.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
			
	}
}





















