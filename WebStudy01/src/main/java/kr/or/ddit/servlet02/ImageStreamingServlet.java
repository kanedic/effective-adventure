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


public class ImageStreamingServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//classpath resource 를 web resource로 변형하여 서비스.
		
//		String filename = req.getParameter("image");
		
		String imageFileName=req.getParameter("image");
		
		ServletContext application = getServletContext();
		String mime = application.getMimeType(imageFileName);
		resp.setContentType(mime);
		
		String imageLogicalPath = "/kr/or/ddit/images/"+imageFileName;
        InputStream is = this.getClass().getResourceAsStream(imageLogicalPath);
		OutputStream out = resp.getOutputStream();
        
        
        byte[] buffer = new byte[1024];
        
		int cnt = -1;
		
		while((cnt = is.read(buffer))!=-1) { //EOF 즉 -1을 만나기 전까지 반복.
			out.write(buffer,0,cnt);
		}
		
		
		
	}
	
	
}
