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
	 
		 //data -> information -> content 의 3단계를 거쳐야함.
		//do계열의 메소드에서는 지역변수
		
		String[] fileNames = folder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
												.map(m->m.startsWith("image/"))
												.orElse(false)
				);
		
		StringBuffer html = new StringBuffer();
		
		//자바 8버전에서는 스트림이라는 반복문을 사용
		//sugar syntax 코드를 간결하게 짜는 경향.
		
		String pattern="<option>%s</option>";
		
		String options = Arrays.stream(fileNames)
							   .map(n->String.format(pattern,n))
							   .collect(Collectors.joining("\n"));
		
//		                                                            
		html.append("<html>                                       " );  
		html.append("<body>                                       " );  
		html.append("<form method='get' action='./streaming.hw'>  " );  
		html.append("<select name='image' onchange='this.form.submit()'> " );  
		html.append(options);  
		html.append("</select>                       " );  
		html.append("</form>		                              " );  
		html.append("</body>                                      " );  
		html.append("</html>                                      " );  
		
		resp.setContentType("text/html;charset=utf-8");
		
		resp.getWriter().print(html);
		
		//토큰 길게 만들고 다시 이클립스에  토크 재설정
		
	}
}
