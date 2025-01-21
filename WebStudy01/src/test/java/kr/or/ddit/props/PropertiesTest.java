package kr.or.ddit.props;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kr.or.ddit.calc.CalcuateServlet;

class PropertiesTest {
//클래스패스 리소스는 논리적 경로로 접근해야함 퀄러파이드네임
	@Test
	void testRead() throws URISyntaxException, IOException {
		Properties props = new Properties();
		///WebStudy01/src/main/resources/kr/or/ddit/props/Dummy.properties
		String qn ="/kr/or/ddit/props/Dummy.properties";
		
//		Path filePath = Paths.get(CalcuateServlet.class.getResource(qn).toURI());
//		논리적 경로를 이용해 물리경로와 인풋스트림을 만듬
//		InputStream is = Files.newInputStream(filePath);
		try(
			InputStream is = CalcuateServlet.class.getResourceAsStream(qn);// 위의 Path와 is를 한줄로 처리함				
			){
		props.load(is);
			
		int size = props.size();
		
		System.out.println(size);
		System.out.println(CalcuateServlet.class.getResource(qn));
		
		
		//맵으로 반복문 하는법
		for( Entry<Object, Object> entry : props.entrySet()) {
			System.out.printf("%s : %s\n",entry.getKey(),entry.getValue() );
		}
		
		}
	}
	
	@Disabled
	@Test
	void testReadAndWrite() throws URISyntaxException, IOException {
		Properties props = new Properties();
		///WebStudy01/src/main/resources/kr/or/ddit/props/Dummy.properties
		String qn ="/kr/or/ddit/props/Dummy.properties";
		Path filePath = Paths.get(CalcuateServlet.class.getResource(qn).toURI());
		
		try(
			InputStream is = CalcuateServlet.class.getResourceAsStream(qn);// 위의 Path와 is를 한줄로 처리함				
			OutputStream out=Files.newOutputStream(filePath);
				){
			props.load(is);
			props.setProperty("newProp3", "newValue3");
//			props.save(out, qn);
			props.store(out, LocalDateTime.now().toString());
		}
	}
}














