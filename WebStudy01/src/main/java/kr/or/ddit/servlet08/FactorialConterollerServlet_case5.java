package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/factorial/case5")
public class FactorialConterollerServlet_case5 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 디스패쳐는 서버에서만 이동할 때 사용 / 서블릿이 할 일을 대신하는 위임 구조를 만들 때 사용
		req.getRequestDispatcher("/WEB-INF/views/factorial/case5Form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String opParam = req.getParameter("operand");
		String accept = req.getHeader("accept");
		resp.setContentType(accept);
		if (opParam != null && !opParam.trim().isEmpty()) {

			int num1 = Optional.of(opParam).filter(op -> op.matches("\\d+")).map(Integer::parseInt)
					.orElseThrow(() -> new IllegalArgumentException("필수 파라밑에 문제 있음"));

			FactorialVO facVO = new FactorialVO();

			facVO.setOperand(num1);

			ObjectMapper mapper = new ObjectMapper();
//			String json = String.format("%d! = %s = %d",facVO.getOperand(),facVO.getExpression(),facVO.getResult());

			Map<String, Object> map = new HashMap<>();
			map.put("oper", facVO.getOperand());
			map.put("ex", facVO.getExpression());
			map.put("res", facVO.getResult());

			String json = mapper.writeValueAsString(map);

//			System.out.println(json);

			try (PrintWriter out = resp.getWriter()) {
				
				mapper.writeValue(out, facVO);
//				mapper.writeValue(out, json);

			}

//			req.setAttribute("facVO", facVO);
//			req.getRequestDispatcher("/WEB-INF/views/factorial/case3Result.jsp").forward(req, resp);

		}

	}

}
