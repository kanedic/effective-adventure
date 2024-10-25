package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * multipart/form-data 수신
 * 	1. post 요청 수신
 * 	2. multi-part body를 수신하기 위해 Part API 활용
 * 	3. @MultipartConfig(multipart-config-web.xml) 설정 필요
 * 		└ 1) 문자 기반의 part는 그냥 parameter로 파싱. //encoding설정은 아님
 * 		└ 2) chunk 단위로 전송되는 2진[binary] 데이터를 저장할 임시 저장소 설정
 * 		└ 3) 업로드 정책 제한 설정 가능
 * 
 * @author PC_02
 *
 */
@WebServlet("/multipart/sendFileAndText")
@MultipartConfig(location = "D:/multipartDir/tempDir",maxFileSize = 1024*1024*10,
maxRequestSize = 1024*1024*10*10,fileSizeThreshold = 1024*10)
public class ReceiveFileAndText extends HttpServlet{
	
//	private File saveDir = new File("D:/multipartDir/savaDir");
	//파일의 경로만을 이용하고 싶을 땐 Paths
    private	Path saveDirPath = Paths.get("D:/multipartDir/savaDir");
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		System.out.printf("param1 : %s \n",req.getParameter("param1"));
		System.out.printf("param2 : %s \n",req.getParameter("param2"));
		System.out.printf("param3 : %s \n",req.getParameter("param3"));

		//업로드 파일을 수신하고, 저장 위치에 저장
		Part uploadFile = req.getPart("uploadFile");
//		uploadFile.entj
		
		//파일 읽기는 part에서 
		if(uploadFile!=null) {
			String originalFileName = uploadFile.getSubmittedFileName();
			Path saveFilePath=Paths.get(saveDirPath.toString(), originalFileName);//경로에 파일 이름으로 파일을 생성
//			saveDirPath.relativize(Paths.get(originalFileName)); 위와 동일한 방법
			uploadFile.write(saveFilePath.toString());//파일의 경로와 이름 //아래의 try구문 생략이 가능
			
//			try(
				InputStream is = uploadFile.getInputStream();//파일을 가져오
//			){
				Files.copy(is, saveFilePath);//파일 복사[3번째 option은 파일 중복이름 처리등의 옵션 부여]
//				System.out.println("업로드 완료");
			//본래는 여기서 스트림 닫는 작업과 임시저장소 제거 작업을 했어야함.
//			}
			String accept=req.getHeader("accept");
			String destPath=null;
			
			if(accept.contains("json")) {		
				destPath="/multipart/fileList"; //
				//응답으로 300코드 페이지에서 새로운 get방식을 요청으로 이동 prg패턴
				resp.sendRedirect(req.getContextPath()+destPath);
				return;
			}else {
				req.setAttribute("savaFilePath", saveFilePath);
				destPath="/WEB-INF/views/multipart/result.jsp";
			}
			req.getRequestDispatcher(destPath).forward(req, resp);				
			
			
			
			
		}
	}
}
















