package kr.or.ddit.flowcontrol.bts;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/homework/btn/*")
@MultipartConfig
public class BtnServlet extends HttpServlet{
	private Map<String, String> btnMap;
	private ServletContext application;
	private Path btnFolderPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		btnMap = new LinkedHashMap<>();	
		
		btnMap.put("bui","뷔");
		btnMap.put("jhop","제이홉");
		btnMap.put("jimin","지민");
		btnMap.put("jin","진");
		btnMap.put("jungkuk","정국");
		btnMap.put("rm","랩몬스터");
		btnMap.put("sugar","슈가");
		
	application = getServletContext(); //서블릿과 대화하기 위한 경로
	 String realPath = application.getRealPath("/WEB-INF/views/bts");//논리 경로를 입력하면 실제 경로를 반환함.
    btnFolderPath = Paths.get(realPath);//mbti 폴더
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String requestURI = req.getRequestURI();//contextPath 뒤의 주소들을 반환
		//[] 한글자 () 그룹 인덱스 1
		
		System.out.println(requestURI);
		System.out.println(req.getContextPath());
		
		Pattern regex = Pattern.compile(req.getContextPath()+"/homework/btn/([a-z]{0,10})$");
		Matcher matcher = regex.matcher(requestURI); //정규식 매칭
		
		if(matcher.find()) {
			System.out.println("fine");
			String member = matcher.group(1);
			System.out.println(member);
			
			singleMember(member, req, resp);
		}else {
			getBtnList(resp);
		}
		//		req.getRequestDispatcher("/resources/html/bts/bui.jsp").forward(req, resp);
	}
	
	private void getBtnList(HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json;charset=utf-8");
		try(
				
				ServletOutputStream ds=resp.getOutputStream();	
				){
			new ObjectMapper().writeValue(resp.getOutputStream(), btnMap);			
		}
	}
	
	private void singleMember(String member,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/WEB-INF/views/bts/"+member+".jsp");
		System.out.println(req.getContextPath()+"/views/bts/"+member+".jsp");
		if(member==null||member.trim().isEmpty()) {
			resp.sendError(400);
			return;
		}
		resp.sendRedirect(req.getContextPath()+"/views/bts/"+member+".jsp");
		
		
	}
	
}










