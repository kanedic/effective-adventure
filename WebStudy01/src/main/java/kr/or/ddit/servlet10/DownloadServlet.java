package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * saveDir 내의 파일의 목록 중 하나를 선택하여 다운로드를 처리함.
 * 
 * 서비스 설계
 * 1.get 요청 처리
 * 2.선택한 파일 명은 filename 파라미터로 전송됨.
 * 3.파일명이 누락된 경우 400 에러코드 전송
 * 4.해당 파일이 없는 경우 , 404 에러코드
 * 5.stream copy
 * 	 - 다운로드 처리될 수 있는 헤더 결정
 * 
 *
 */
@WebServlet("/multipart/download.do")
public class DownloadServlet extends HttpServlet{
	private Path saveDirPath = Paths.get("D:/multipartDir/savaDir");
	private ServletContext application;
	@Override
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			super.init(config);
			application = getServletContext();
		}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
//		1. charset 설정
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
//		2. filename 파라미터 확보
		String fileName = req.getParameter("filename");

//		3. 누락 여부 체크 - 누락일 시 400
		if(fileName==null||fileName.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"파일 명 누락");
			return;
		}
		
		//이 경로에 파일 이름을 합침 실제 파일의 경로
		Path filePath = saveDirPath.resolve(fileName);
		File file = filePath.toFile();
		
//		4. 파일 존재 여부 체크 - 누락일 시 404
		if(!file.exists()) {
			resp.sendError(404,"요청 파일은 존재하지 않음");
			return;			
		}
		
		if(file.isDirectory()) {//파일이 아니라면
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"폴더는 다운로드가 안됨");
			return;
		}
		
		
//		5. 다운로드가 가능하도록 헤더 설정 (content-Disposition-attatchment,filename)
		String encodedFilename = URLEncoder.encode(fileName,"utf-8").replace("+"," ");
		resp.setHeader("Content-Disposition",String.format("attatchment;filename=\"%s\"",encodedFilename));
		
		String mime = Optional.ofNullable(application.getMimeType(fileName))
							  .orElse("application/octet-stream");
		//만약 널이면 /octet-stream 으로 지정
		
		resp.setContentType(mime);
		resp.setContentLengthLong(file.length()); //파일의 크기를 알려주면 브라우저에서 %표시가 가능
		
		
		
//		6. 입/출력 스트림 확보 -- body 기록 
		try(
			OutputStream os=resp.getOutputStream();	
				){
			Files.copy(filePath, os);
		}
		
	}
}















