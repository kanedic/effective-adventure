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
	
	//web.xml에서 선언한 값[파일 경로]을 가져오기 위한 전역변수.
	/*	 <context-param>
  	 	<param-name>folderQN</param-name>
  	 	<param-value>/kr/or/ddit/images</param-value>  	 
  		</context-param>
	 * 
	 */
	private ServletContext application;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
		//파일 경로는 서블릿이 실행 될 때 처음 그리고 한번만 호출 되게 하면 된다.
		//그렇기에 init 메소드에서 얻은 후에 전역변수로 관리한다.
		
		//자바 API로 널포인트에 안전한 코드 만들기
		// 널 일수도 있음	folderQN을 qn으로 화살표함수 - 자바 남다
		
		
		/*
		 * 널 일수도 있는 app객체의 folderQN이라는 값을 가져온다.
		 * null이 아니라면 folderQN의 값이 담긴 qn이란 변수를 이용해 현재 클래스의 기준으로 절대경로를 생성한다.
		 * 절대경로. 즉 url이라는 변수를 생성하고 url의 경로에 해당하는 곳의 File 정보들을 가져온다.
		 * File정보들이 담긴 rp라는 변수로 자바에서 새로운 File 객체를 생성한다 new File(rp)
		 * 
		 * 이러면 해당 경로에 존재하는 파일에 대한 null체크가 끝
		 * 
		 */
		
		folder= Optional.ofNullable(application.getInitParameter("folderQN"))
						.map(qn->this.getClass().getResource(qn))
						.map(url->url.getFile())
						.map(rp->new File(rp))
						.orElseThrow(()->new ServletException("폴더가 존재하지 않습니다."));
			
		System.out.println(folder.getAbsolutePath());
		
	//
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * imageFile에 들어가는 값은 null일 수도 있다.
		 * 만약 Optional의 과정중 null이 발생하면 orElseThrow() 구문. 즉 오류를 고의적으로 발생시켜
		 * 서비스를 이용 못하게 막는다.
		 * 요청의 파라미터로 image로 넘어온 값을 받는다. 이는 imageFormServlet에서 생성한 form 태그의 결과로
		 * image 값으로 이미지 파일의 이름. 즉 cute2.JPG 등이 파라미터로 넘어온다.
		 * 
		 * map을 이용해 가져온 파라미터 값을 바로 사용한다. 넘어온 파라미터 값을 p라는 변수로 설정.
		 * 새로운 File 객체를 만든다. 여기서 주소값은 init에서 Optional의 결과로 생성된 전역변수 folder와
		 * 파라미터값으로 넘어온 p 를 조합한다.
		 * 위의 결과로 나온 File 객체를 f로 설정하고 f의 존재 여부 filter 사용해 한 번 더 검증한다. 
		 * 
		 * 여기까지가 파일 읽기 끝.
		 */
		
		
		File imageFile=Optional.ofNullable(req.getParameter("image"))
								.map(p->new File(folder,p))
								.filter((f)->f.exists())
								.orElseThrow(()->new ServletException("필수 파라미터 누락"));
		
		
		
		//요청에 대한 응답을 설정하기 전 응답 컨텐츠의 타입을 지정한다.
		//같은 이미지 파일이어도 JPG,PNG,GIF,JPEG 등등 확장자가 모두 다르기에
		//이를 유동적으로 잡아내기 위한 처리를 한다.
		//getMimeType을 이용해 필터링이 끝난 파일 imageFile의 이름을 가져오고 이를 타입으로 설정한다,
		resp.setContentType(application.getMimeType(imageFile.getName()));
		
		//클로즈가 가능한 객체 input/output stream을 넣으면 close를 자동으로 해줌
//		try(Closable 객체) {}catch(exception) {}finally {} try with resource 구문
		try(OutputStream os= resp.getOutputStream()){
			//해당 파일의 경로에 os 아웃스트림을 실행. 
			Files.copy(imageFile.toPath(),os);
		}
		
		
		
		
		
		
		
	}
	
}
