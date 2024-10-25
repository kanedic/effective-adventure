package kr.or.ddit.servlet11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 1. 서비스 설계
 * 
 * 1) head 메소드 : no-op handler [요청을 받기만] 2) get 메소드 : 자원 서비스 -JSON , HTML 컨텐츠로
 * 서비스 3) post 메소드 : 자원 수신 - JSON payload , request.parameter , multipart 컨텐츠 수신
 * - 수신한 자원에는 필수 데이터(DATA3)가 포함되어있는지 검증함.
 * 
 * 
 */
@WebServlet("/status/send-and-receive")
public class ResponseStatusCaseServlet extends HttpServlet {
	Map<String, Object> model = new HashMap<>();

	// 코드블럭 - 생성자의 역할을 대신함 서블릿의 init?
	@Override
	public void init() throws ServletException {
		super.init();
		model.put("data1", "DATA1");
		model.put("data2", "DATA2");
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Dummy Content"); // response body를 통해 전송이 되는가?
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp); 이걸 냅두면 오류 405

		// 401 CODE
		String autiorzaion = req.getHeader("authorization");// 이 헤더가 존재하면 인증된 클라라는 뜻
		if (autiorzaion == null) {
			resp.setHeader("www-authenticate", "basic");// 브라우저가 지닌 basic 로그인 시스템으로 인도
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증되지 않은 사용자");
			return;
		}

		String userToken = autiorzaion.split("\\s+")[1];
		userToken = new String(Base64.getDecoder().decode(userToken));// 바이트단위를 다시 문자열로 묶기

		if (!userToken.startsWith("admin")) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 접근할 수 있습니다.");
			return;
		}

		System.out.printf("사용자 식별 정보 : %s \n", userToken);
		String accept = Optional.ofNullable(req.getHeader("accept")).map(String::toLowerCase).orElse("");

		String contentType = null;
		String content = null;

		if (accept.contains("json")) {
			// json 생성
			contentType = "application/json;charset=utf-8";
			content = new ObjectMapper().writeValueAsString(model);
		} else if (accept.contains("xml")) { // 요청 협상 실패
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "xml 컨텐츠는 서비스 불가");
			return;
		} else {
			// html 생성
			contentType = "text/html;charset=utf-8";
			content = String.format("<h4>%s</h4>", model.toString());
		}

		resp.setContentType(contentType);

		try (PrintWriter out = resp.getWriter();) {
			out.print(content);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String contentType = Optional.ofNullable(req.getContentType()).orElse("");
		// 확실하지 않을 때 ? 메타문자 객체의 형태로 ?가 결정됨
		Map<String, ?> requestContent = null;

		if (contentType.contains("json")) {
			// json payload
			requestContent = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);

		} else if (contentType.contains("xml")) { // 요청 협상 실패
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "xml 페이로드 수신 불가");
			return;
		} else {
			// request parameter
			requestContent = req.getParameterMap();
		}

		Object data3 = requestContent.get("data3");
		if (data3 == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "요청이 검증을 통과하지 못하였습니다.");
			return;
		} else {
			model.put("data3", data3);
//			Post - Redirect - Get : PRG 패턴 설계 결국에는 get방식 요청 발생
			resp.sendRedirect(req.getContextPath() + "/status/send-and-receive");
		}

	}

}
