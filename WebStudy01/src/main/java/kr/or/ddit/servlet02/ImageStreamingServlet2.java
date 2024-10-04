package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImageStreamingServlet2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//classpath resource 를 web resource로 변형하여 서비스.
		
//		String filename = req.getParameter("image");
		
		//요청에 대한 검증 파라미터의 존재 유무를 확인하고 없을 때의 상황을 처리
		
		String imageFileName=req.getParameter("image");
		
		//								그냥 공백만 입력
		if(imageFileName==null || imageFileName.trim().length()==0) {
			//예외를 직접 만들어서 처리
			throw new ServletException("필수 파라미터 누락");
			
		}
		
		
		ServletContext application = getServletContext();
		String mime = application.getMimeType(imageFileName);
		resp.setContentType(mime);
		
		String imageLogicalPath = "/kr/or/ddit/images/"+imageFileName;
		//파일 이름에 해당되는 정보를 읽음
        InputStream is = this.getClass().getResourceAsStream(imageLogicalPath);
        
        if(is==null) {
        	throw new ServletException("이름에 해당하는 이미지 파일이 없습니다.");
        }
        
		OutputStream out = resp.getOutputStream();
        
		
        byte[] buffer = new byte[1024];
        
		int cnt = -1;
		
		//대부분의 null은 . 앞에 있는 변수에 의해 나올 가능성이 높음
		//읽은 정보를 내보냄.
		while((cnt = is.read(buffer))!=-1) { //EOF 즉 -1을 만나기 전까지 반복.
			out.write(buffer,0,cnt);
		}
		
		
		
	}
	
	
}
