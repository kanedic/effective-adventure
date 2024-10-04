package kr.or.ddit.servlet03;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageStreamingServlet extends HttpServlet{

	private File folder;
	private ServletContext application;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
		//파일 경로는 서블릿이 실행 될 때 처음 그리고 한번만 호출 되게 하면 된다.
		//그렇기에 init 메소드에서 얻은 후에 전역변수로 관리한다.
		
		//자바 API로 널포인트에 안전한 코드 만들기
		// 널 일수도 있음	folderQN을 qn으로 화살표함수 - 자바 남다
		folder= Optional.ofNullable(application.getInitParameter("folderQN"))
						.map(qn->this.getClass().getResource(qn))
						.map(url->url.getFile())
						.map(rp->new File(rp))
						.orElseThrow(()->new ServletException("폴더가 존재하지 않습니다."));
			
		System.out.println(folder.getAbsolutePath());
		
	
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File imageFile=Optional.ofNullable(req.getParameter("image"))
								.map(p->new File(folder,p))
								.filter((f)->f.exists())
								.orElseThrow(()->new ServletException("필수 파라미터 누락"));
		
		//클로즈가 가능한 객체 input/output stream을 넣으면 close를 자동으로 해줌
//		try(Closable 객체) {}catch(exception) {}finally {} try with resource 구문
		
		
		
		resp.setContentType(application.getMimeType(imageFile.getName()));
		
		try(OutputStream os= resp.getOutputStream()){
			Files.copy(imageFile.toPath(),os);
		}
		
		
		
		
		
		
		
	}
	
}
