package kr.or.ddit.servlet10;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/multipart/fileList")
public class FileList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Path saveDirPath = Paths.get("D:/multipartDir/savaDir");
//		File folder= new File(saveDirPath.toString());
//		
//		String[] list = folder.list();
//		
//		req.setAttribute("list", folder);
//		req.setAttribute("saveDirPath", saveDirPath);
//		req.getRequestDispatcher("/WEB-INF/views/multipart/result.jsp").forward(req, resp);
		
//		File file = new File("D:/multipartDir/savaDir");
		
		List<Path> children=new ArrayList<>();
		
		//한 디렉토리 안의 모든 파일을 선별해야할때
		Files.walkFileTree(saveDirPath, new SimpleFileVisitor<Path>() {
			@Override//파일에 들어갔을 때 해당 폴더의 파일을 리스트에 추가
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				children.add(file);//여기서 파일 명만 담거나
				return super.visitFile(file, attrs);
			}
		});
		
		//children의 내부를 한번 매핑해서 패스를 거르기
		String[] fileNames=children.stream()
								.map(p->p.getFileName().toString())
								.toArray(String[]::new);
		
//		String[] fileNames=file.list();
		
		ObjectMapper mapper = new ObjectMapper();
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		try(
				 PrintWriter out=resp.getWriter();
				){
			mapper.writeValue(out, fileNames);
		}		
	}
}
