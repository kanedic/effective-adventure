package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

//@WebServlet("/image/formUI.hw") 이부분 겹쳐서 오류뜬거임
public class FormUI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
       PrintWriter out = resp.getWriter();
       String imagePath = "/kr/or/ddit/images";
       // 실제 파일 시스템의 경로 가져오기
       URL imageFolderURL = getClass().getResource(imagePath);
       File imageFolder = new File(imageFolderURL.getFile());
       
   
       out.append("<html>");
       out.append("<body>");
     String[] filenames = imageFolder.list();
     for (String filename : filenames) {
    	out.append("<a href='../image/streaming.do?image="+filename+"'>"+ filename+ " </a><br>");
     }
     
     	out.append("</html>");
    	out.append("</body>");
    	
    }
}
