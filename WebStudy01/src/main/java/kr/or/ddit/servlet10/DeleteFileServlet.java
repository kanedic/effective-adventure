package kr.or.ddit.servlet10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/multipart/deleteFile")
public class DeleteFileServlet extends HttpServlet{
    private	Path saveDirPath = Paths.get("D:/multipartDir/savaDir");

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("asdasdasd");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		 StringBuilder sb = new StringBuilder();
	        String line;
	        try (BufferedReader reader = req.getReader()) {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	        }
		
		ObjectMapper mapper = new ObjectMapper();
        String[] deleteFiles = mapper.readValue(sb.toString(), String[].class);
		
        for (String fileName : deleteFiles) {
            System.out.println("선택된 파일들 " + fileName);
           
        }
		System.out.println("asdasdasd");
		
	
		
		
		
	}
}





