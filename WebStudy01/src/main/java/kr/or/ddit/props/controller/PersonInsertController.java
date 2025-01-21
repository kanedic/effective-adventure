package kr.or.ddit.props.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;

/**
 * 새로운 person을 등록하고 나면
 * 갱신된 list 조회
 * 
 * 최근 등록된 person은 백그라운드로 강조
 */
@WebServlet("/props/personInsert.do")
public class PersonInsertController extends HttpServlet{
	private PersonService service = PersonServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
///WebStudy01/src/main/webapp/WEB-INF/views/props/personForm.jsp
		//여기로오면 personForm으로 이동시키기.
		
		req.getRequestDispatcher("/WEB-INF/views/props/personForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//post로 데이터를 받고 성공하면 redirect로 리스트로 돌아가기
		//인코딩 하고 널체크 하고 null 나오면 에러 띄우고 아니면 정상적으로 호출해서 vo 전달하기
		resp.setCharacterEncoding("utf-8");
		
		String id      = req.getParameter("id");
		String name    = req.getParameter("name");
		String gender  = req.getParameter("gender");
		String age     = req.getParameter("age");
		String address = req.getParameter("address");
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(address);
		
		PersonVO person = new PersonVO();
		person.setId(id);
		person.setName(name);
		person.setGender(gender);
		person.setAge(age);;
		person.setAddress(address);
		
		
		Map<String,String>errors = new HashMap<>();
		validate(person,errors);
		boolean valid = errors.isEmpty();
		
		if(valid) {
			boolean result = service.createPerson(person);
			
			if(result) {
				req.getSession().setAttribute("newPerson", person);
				
				resp.sendRedirect(req.getContextPath()+"/props/personList.do");				
			}else {
				resp.sendError(500,"일신상의 사유로 작업 실패");								
			}
			
		}else {
			resp.sendError(400,errors.toString());				
		}
		
	}

	private void validate(PersonVO person,Map<String,String>errors) {
		boolean valid = true;
		if(StringUtils.isBlank(person.getId())) {
			valid=false;
			errors.put("id","아이디 누락");
		}
		if(StringUtils.isBlank(person.getName())) {
			valid=false;
			errors.put("name","이름 누락");
		}
		if(StringUtils.isBlank(person.getName())) {
			valid=false;
			errors.put("gender","성별 누락");
		}
		if(StringUtils.isBlank(person.getName())) {
			valid=false;
			errors.put("age","나이 누락");
		}
		if(StringUtils.isBlank(person.getName())) {
			valid=false;
			errors.put("address","주소 누락");
		}
	}
}





















