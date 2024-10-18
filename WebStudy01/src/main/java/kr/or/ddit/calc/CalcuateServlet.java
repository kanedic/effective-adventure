package kr.or.ddit.calc;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/calculate")
@MultipartConfig
public class CalcuateServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get과 포스트에서 사용할 공통 인코딩
		req.setCharacterEncoding("utf-8");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/calc/calForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	double left=Optional.of(req.getParameter("left"))
						.map(Double::parseDouble)
						.get();//예외가 알아서 만들어짐

	double right=Optional.of(req.getParameter("right"))
			.map(Double::parseDouble)
			.get();//예외가 알아서 만들어짐
		
	OperatorType operator=	Optional.of(req.getParameter("operator"))
									.map(OperatorType::valueOf)
									.get();
		
	CalculateVO calVo = new CalculateVO();
	
	calVo.setLeft(left);
	calVo.setOperator(operator);
	calVo.setRight(right);
	//메소드 레퍼런스 종류
	resp.setContentType("application/json;charset=utf-8");
		
	new ObjectMapper().writeValue(resp.getWriter(), calVo);
	
		
	}
}















