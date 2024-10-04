package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageFormServlet extends HttpServlet{
	private File folder;
	private ServletContext application;
	//web.xml에서 선언한 값[파일 경로]을 가져오기 위한 전역변수.
		/*	 <context-param>
	  	 	<param-name>folderQN</param-name>
	  	 	<param-value>/kr/or/ddit/images</param-value>  	 
	  		</context-param>
		 * 
		 */

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
		 * m, 즉 MimeType이 startWith("image/") , image/로 시작하는 파일들만
		 * filesNames 배열에 담는다. 
		 * 
		 */
		
		
		String[] fileNames = folder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
												.map(m->m.startsWith("image/"))
												.orElse(false)
				);
		
		
		
		StringBuffer html = new StringBuffer();
		
		//자바 8버전에서는 스트림이라는 반복문을 사용
		//sugar syntax 코드를 간결하게 짜는 경향.
		
		
		String pattern="<option>%s</option>";

		//stream의 인자로 배열을 줄것이기에 Arrays.stream() 사용
		//변수로 String[] fileNames를 사용한다.
		//fileNames의 데이터를 순환하며 데이터에 n이라는 변수로 이름을 붙히고
		//String.format(패턴,문자) 을 하면 문자열의 %s 부분에 문자부분을 넣는다.
		//배열을 순환하면서 작업을 진행.
		//collect 명령어를 사용해 하나하나의 결과를 options 변수 하나에 몰아준다.

		String options = Arrays.stream(fileNames)
							   .map(n->String.format(pattern,n))
							   .collect(Collectors.joining("\n"));
		
//		                                                            
		html.append("<html>                                       " );  
		html.append("<body>                                       " );  
		html.append("<form method='get' action='./streaming.hw'>  " ); 
		// onchange='this.form.submit()
		// select 요소에 change가 발생하면 그에 해당하는 값을 form을 이용해 submit. 서블릿으로 보낸다.
		html.append("<select name='image' onchange='this.form.submit()'> " );  
		html.append(options);  
		html.append("</select>                       " );  
		html.append("</form>		                              " );  
		html.append("</body>                                      " );  
		html.append("</html>                                      " );  
		
		resp.setContentType("text/html;charset=utf-8");
		
		resp.getWriter().print(html);
		
		
	}
}
