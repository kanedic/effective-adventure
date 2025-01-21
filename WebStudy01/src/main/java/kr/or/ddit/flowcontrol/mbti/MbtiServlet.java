package kr.or.ddit.flowcontrol.mbti;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
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
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RestFul URI 
 * 	/mbti (GET)
 * 	/mbti/enfj (GET) : path variable 경로변수 의 형태로 데이터를 수신하기도 함.
 * 	/mbti (POST)
 * 	/mbti (DELETE) 전체삭제
 * 	/mbti/enfj (DELETE) : 단품 삭제 
 * 
 */
@WebServlet("/mbti/*")
@MultipartConfig
public class MbtiServlet extends HttpServlet{
	
	public Map<String, String> mbtiMap;
//	private Map<String, String> mbtiMap;
	private ServletContext application;
	private Path mbtiFolderPath;
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		mbtiMap = new LinkedHashMap<>();//연결성을 지닌 맵으로 저장하기 때문에 순서가 일정해짐

        mbtiMap.put("istj","1. ISTJ 소금형");
        mbtiMap.put("isfj","2. ISFJ 권력형");
        mbtiMap.put("infj","3. INFJ 예언자형");
        mbtiMap.put("intj","4. INTJ 과학자형");
        mbtiMap.put("istp","5. ISTP 백과사전형");
        mbtiMap.put("isfp","6. ISFP 성인군자형");
        mbtiMap.put("infp","7. INFP 잔다르크형");
        mbtiMap.put("intp","8. INTP 아이디어형");
        mbtiMap.put("estp","9. ESTP 활동가형");
        mbtiMap.put("esfp","10. ESFP 사교형");
        mbtiMap.put("enfp","11. ENFP 스파크형");
        mbtiMap.put("entp","12. ENTP 발명가형");
        mbtiMap.put("estj","13. ESTJ 사업가형");
        mbtiMap.put("esfj","14. ESFJ 친선도모형");
        mbtiMap.put("enfj","15. ENFJ 언변능숙형");
        
        application = getServletContext(); //서블릿과 대화하기 위한 경로
        String realPath = application.getRealPath("/WEB-INF/views/mbti/mbtitypes");//논리 경로를 입력하면 실제 경로를 반환함.
        mbtiFolderPath = Paths.get(realPath);//mbti 폴더
	}
	
	private void singleMbti(String type,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(type==null||type.trim().isEmpty()) {
			resp.sendError(400);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/views/mbti/mbtitypes/"+type+".html").forward(req, resp);
		
		
	}
	
	private void allMbti(HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json;charset=utf-8");
		try(
//				ServletOutputStream ds=resp.getOutputStream();	
				ServletOutputStream ds=resp.getOutputStream();	
				){
			new ObjectMapper().writeValue(resp.getOutputStream(), mbtiMap);			
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String requestURI = req.getRequestURI();//contextPath 뒤의 주소들을 반환
		//[] 한글자 () 그룹 인덱스 1
		Pattern regex = Pattern.compile(req.getContextPath()+"/mbti/([a-z]{4})");
		Matcher matcher = regex.matcher(requestURI); //정규식 매칭
		
		if(matcher.find()) {
			String type = matcher.group(1);
			
			singleMbti(type, req, resp);
		}else {
			allMbti(resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 특수문자에 대한 디코딩 설정
		req.setCharacterEncoding("utf-8");
		
		// 2. 문자 기반의 파트는 파라미터로 , 파일기반은 Part로 수신
		String type = req.getParameter("mbtiType");
		String desc = req.getParameter("mbtiDesc");
		
		Part mbtiFile = req.getPart("mbtiFile"); //이게 파일을 하나 받아옴
		
		boolean valid = true;
		Map<String,String> errors=new HashMap<>();
		
		// 3. 검증 (모든 데이터)
		if(StringUtils.isBlank(type)) {
			valid=false;
			errors.put("type","유형 데이터 누락");
		}
		if(StringUtils.isBlank(desc)) {
			valid=false;			
			errors.put("desc","설명 데이터 누락");
		}
		if(mbtiFile==null||StringUtils.isBlank(mbtiFile.getSubmittedFileName())) {
			errors.put("mbtiFile","mbti 파일 누락");
			valid=false;
		}else if(!mbtiFile.getContentType().contains("html")) {
			//등록 파일에 대한 마임 체크를 하고 grml이 맞는지 확인
			valid=false;
			errors.put("desc","적합하지 않은 파일 확장자");
		}
		
		
		if(valid) {
			// 5. 새로운 MBTI 유형 등록 - mbtiMap에 엔트리 추가 - 컨텐츠 파일 저장
			mbtiMap.put(type,desc);
			
			Path filePath = mbtiFolderPath.resolve(type+".html"); //실제로 저장할 주소
			mbtiFile.write(filePath.toString());
			// 6. redirect로 응답 설정 주소는 /mbti 그럼 get 메소드 실행
			
			resp.sendRedirect(req.getContextPath()+"/mbti");
			
			
		}else {
			// 4. 검증 시류ㅐ : 400에러
			resp.sendError(400,errors.toString());
		}
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
		
		String requestURI = req.getRequestURI();
		//[] 한글자 () 그룹 인덱스 1
		Pattern regex = Pattern.compile(req.getContextPath()+"/mbti/([a-z]{4})");
		Matcher matcher = regex.matcher(requestURI); //정규식 매칭
		
		if(matcher.find()) {
			String type = matcher.group(1);
			mbtiMap.remove(type); //맵에서 삭제
			
			//파일 삭제
			Path delFilePath = mbtiFolderPath.resolve(type+".html");
			boolean success = Files.deleteIfExists(delFilePath); //존재 하면 지워라
			//resp.sendRedirect(req.getContextPath()+"/mbti"); //put delete 후의 redirect는 다시 put과 delete 메소드를 타버림.
			
			Map<String,Object> target = Collections.singletonMap("deleted", success? 1:0); //지우기
			
			resp.setContentType("application/json;charset=utf-8");
			
			try(
				OutputStream out =	resp.getOutputStream();
					){
				new ObjectMapper().writeValue(out, target);
			}
		}else {
			//경로가 안넘어오면 전체삭제 가 맞긴한데 에러 출력으로 대체
			resp.sendError(405,"전체 삭제는 지원하지 않는 기능입니다."); 
		}
		
	}
	
}


















