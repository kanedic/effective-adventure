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


@WebServlet(value="/movie/csr/imagefileList",loadOnStartup = 1)
public class ClientSideRenderingImageFileListJsonController extends HttpServlet{
	private File folder;
	private ServletContext application;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
				System.out.println(application.getInitParameter("imageFolderQN"));
		
				folder= Optional.ofNullable(application.getInitParameter("imageFolderQN"))
						.map(qn->this.getClass().getResource(qn))
						.map(url->url.getFile())
						.map(rp->new File(rp))
						.orElseThrow(()->new ServletException("폴더가 존재하지 않습니다."));
	
		System.out.println(folder.getAbsolutePath());
	
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
		
		
		String[] fileNames = folder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
												.map(m->m.startsWith("image/"))
												.orElse(false)
				);
		
		System.out.println(fileNames);
		
		
		
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
		
//		["name1","name2"]
		
		//JSON데이터의 형식은 위와같은 형태로 이루어져 있거나
		//[{"key1":"val1"},{"key2":"val2"}] 의 형식으로 이루어져 있다.
		//여기서는 1번의 형식을 사용
		//elements의 결과를 JSON형태로 재포장한다. format 사용.
		String json = String.format("[%s]", elements);
		
		
		//request에 대한 결과로 나온 Content의 Type이 Json 형태이니
		//컨텐트타입을 json으로 인식하도록 설정한다.
		resp.setContentType("application/json;charset=utf-8");
		
		try(
			// 1. *.close 가 가능한 객체 선언 3.전송 종료와 동시에 close
			PrintWriter	out=resp.getWriter();
				){
			// 2. 만든 json값을 jsp 파일로 전송
			out.println(json);
		}
		
		
	}
}
