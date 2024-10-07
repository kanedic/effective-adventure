package kr.or.ddit.servlet05;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이거 지우기
@WebServlet(value="/movie/csr/ALLfileList",loadOnStartup = 1)
public class ClientSideRenderingALLFileListJsonController extends HttpServlet{
	private File folder;
	private File imageFolder;
	private ServletContext application;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
		folder= Optional.ofNullable(application.getInitParameter("movieFolder"))
						.map(rp->new File(rp))
						.orElseThrow(()->new ServletException("폴더가 존재하지 않습니다."));
			
		imageFolder= Optional.ofNullable(application.getInitParameter("imageFolderQN"))
				.map(qn->this.getClass().getResource(qn))
				.map(url->url.getFile())
				.map(rp->new File(rp))
				.orElseThrow(()->new ServletException("폴더가 존재하지 않습니다."));

	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
		 //data -> information -> content 의 3단계를 거쳐야함.
		//do계열의 메소드에서는 지역변수
		
		/*
		 * init에서 web.xml/서블리의 정보를 가져올 application (이하 app)와
		 *  file의 정보를 담은 folder 전역변수를 생성한 상태.
		 * 	
		 * 파일 이름들이 담긴 fileNames 배열은 다음과 같은 명령어로 산출된다.	
		 * Optional 을 이용하여 만약 파일에 대한 해당 과정중에 null이 발생하면 결과값으로 false가 나오며
		 * 명령이 진행중이던 파일은 멈추고 다른 파일을 진행한다. = orElse
		 * 
		 * .ofNullable() : null이 발생할 수도 있는 값에 사용하는 문법. null이 발생하면 orElse로 넘긴다.
		 * 
		 * d는 디렉토리 n은 파일명. app.getMimeType(n)으로 파일명에 존재하는 확장자,
		 * MimeType을 찾아낸다. 이 Mime의 값을 m이라는 변수로 지정하고
		 * 비디오의 MimeType의 시작은 video/ 임을 이용하여
		 * m, 즉 MimeType이 startWith("video/") , video/로 시작하는 파일들만
		 * filesNames 배열에 담는다. 
		 * 이러면 같은 폴더 안에 존재하는 image나 text 등의 다른 MimeType 파일들은 모두 패스하고
		 * mp4, 비디오 파일만 String 배열에 담는다.
		 * 
		 */
		
		
		String[] fileNames = folder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
												.map(m->m.startsWith("video/"))
												.orElse(false)
				);
		
		String[] imageFileNames = imageFolder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
				.map(m->m.startsWith("image/"))
				.orElse(false)
);
		
		
		//자바 8버전에서는 스트림이라는 반복문을 사용
		//sugar syntax 코드를 간결하게 짜는 경향.
	
		
		String pattern="\"%s\"";

		//stream의 인자로 배열을 줄것이기에 Arrays.stream() 사용
		//변수로 String[] fileNames를 사용한다.
		//fileNames의 데이터를 순환하며 데이터에 n이라는 변수로 이름을 붙히고
		//String.format(패턴,문자) 을 하면 문자열의 %s 부분에 문자부분을 넣는다.
		//하나의 작업이 끝나면 마지막에 ',' 을 붙힌다. 배열을 순환하면서 작업을 진행.
		//"data1","data2",...의 형식


		String elements = Arrays.stream(fileNames)
							   .map(n->String.format(pattern,n))
							   .collect(Collectors.joining(","));
		
		String imageElements = Arrays.stream(imageFileNames)
				   .map(n->String.format(pattern,n))
				   .collect(Collectors.joining(","));

		
		
//		["name1","name2"]
		
		//JSON데이터의 형식은 위와같은 형태로 이루어져 있거나
		//[{"key1":"val1"},{"key2":"val2"}] 의 형식으로 이루어져 있다.
		//여기서는 1번의 형식을 사용
		//elements의 결과를 JSON형태로 재포장한다. format 사용.
		String json = String.format("[%s",elements);
		String json2 = String.format("%s]",imageElements);
		String json3 = json+","+json2;
		
		System.out.println(json);
		System.out.println(json2);
		System.out.println(json3);
		
		//request에 대한 결과로 나온 Content의 Type이 Json 형태이니
		//컨텐트타입을 json으로 인식하도록 설정한다.
		resp.setContentType("application/json;charset=utf-8");
		
		try(
			// 1. *.close 가 가능한 객체 선언 3.전송 종료와 동시에 close
			PrintWriter	out=resp.getWriter();
				){
			// 2. 만든 json값을 jsp 파일로 전송
//			out.println(json);
//			out.println(json2);
			out.println(json3);
		}
		
		
	}
}
