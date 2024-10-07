package kr.or.ddit.servlet05;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/movieOrImage.hw")
public class movieOrImage extends HttpServlet{

	private File folder;
	
	private ServletContext application;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
		folder= Optional.ofNullable(application.getInitParameter("imageFolderQN"))
						.map(qn->this.getClass().getResource(qn))
						.map(url->url.getFile())
						.map(rp->new File(rp))
						.orElseThrow(()->new ServletException("폴더가 존재하지 않습니다."));
			
		System.out.println(folder.getAbsolutePath());
		
	//
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String g = req.getParameter("movieOrImage");
		
		String mime = application.getMimeType(g);
		System.out.println(g);
		System.out.println(mime);
		if(mime.startsWith("video")) {
			req.getRequestDispatcher("/movie/streaming.hw").forward(req, resp);
		}
		
		File imageFile=Optional.ofNullable(req.getParameter("movieOrImage"))
								.map(p->new File(folder,p))
								.filter((f)->f.exists())
								.orElseThrow(()->new ServletException("필수 파라미터 누락"));
		
		
		
		resp.setContentType(application.getMimeType(imageFile.getName()));
		try(OutputStream os= resp.getOutputStream()){
			//해당 파일의 경로에 os 아웃스트림을 실행. 
			Files.copy(imageFile.toPath(),os);
			
		}
		
		
		
		
		
		
		
	}
	
}
