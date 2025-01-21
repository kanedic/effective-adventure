package kr.or.ddit.datashare;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.flowcontrol.mbti.MbtiServlet;

class DataAccessBetweenObjects {

//	static class TypeA{
//		private String propA ="DATA - A";
//		{
//			TypeB tb = new TypeB();
//			tb.propB;
//		}
//	}
//	
//	static class TypeB{
//		public String propB ="DATA - B";		
//	}
	
	@Test //서블릿의 init이 실행되지 않아 null로 출력이 됨.
	void test() {
		MbtiServlet instance = new MbtiServlet();
		//왜? 컨텍스트 톰캣이 실행하면서 init메소드로 map에 데이터를 put 해야하는데 그걸 하지 못하고
		//그냥 생성만 한 mbtiMap을 강제로 가져온 것.
		System.out.println(instance.mbtiMap);
	}

}
