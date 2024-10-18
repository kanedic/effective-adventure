package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/factorial/case6")
public class FactorialConterollerServlet_case6 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 디스패쳐는 서버에서만 이동할 때 사용 / 서블릿이 할 일을 대신하는 위임 구조를 만들 때 사용
		req.getRequestDispatcher("/WEB-INF/views/factorial/case6Form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSON데이터를 받으려면 getParameter가 아닌 다른방식
		//0과 1로 이루어진 데이터를 읽을 input스트림이 필요
		ObjectMapper mapper = new ObjectMapper();
		InputStream is = req.getInputStream();
		FactorialVO facVO = mapper.readValue(is, FactorialVO.class);

		String accept = req.getHeader("accept");
		resp.setContentType(accept);

		try (PrintWriter out = resp.getWriter()) {

			mapper.writeValue(out, facVO);

		}


	}

}
