package kr.or.ddit.jackson;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import kr.or.ddit.servlet08.FactorialVO;

class JsonMarshallingTest {

	@Test
	void testVO() throws StreamWriteException, DatabindException, IOException {
		FactorialVO facVo = new FactorialVO();
		facVo.setOperand(5);
		
		ObjectMapper mapper = new ObjectMapper();
		
		//직렬화 메소드
		//마샬링과 직렬화를 한번에 출력 스트림, 값
//		mapper.writeValue(System.out, facVo);
		
		String json = "{\"operand\":5,\"result\":120,\"expression\":\"5 * 4 * 3 * 2 * 1\"}";
		
		FactorialVO unMarshalledObj = mapper.readValue(json, FactorialVO.class);
		
		System.out.println(unMarshalledObj);
		
		
	}
	
	
	
	@Disabled
	@Test
	void test() throws JsonProcessingException {
		
		Map<String, Object> nativeTarget = new HashMap<>();
		nativeTarget.put("prop1","value1");
		nativeTarget.put("prop2",23333);
		nativeTarget.put("prop3",true);
		nativeTarget.put("prop4",Collections.singletonMap("innerProp", "innerValue"));
		nativeTarget.put("prop5",new int[] {1,2,3});
		
		ObjectMapper mapper = new ObjectMapper();
//		ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
		//writer계열 마샬링 메소드
		//read계열 언마샬링 메소드
		String json = mapper.writeValueAsString(nativeTarget);
		
		System.out.printf("마샬링 된 json : %s \n",json);
		
		HashMap<String,Object> unMarshalledObj = mapper.readValue(json, HashMap.class);
		
		System.out.printf("언마샬링 된 unMarshalledObj : %s \n",unMarshalledObj);
		
		
		
		
		
		
	}

}
