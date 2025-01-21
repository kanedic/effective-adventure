package kr.or.ddit.props;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

class ResourceBundleTest {

	@Test
	void test() {
		//baseName은 쿼러파이드 네임 약간 변경됨. 확장자 없음 /는 .으로 대체
		String baseName = "kr.or.ddit.props.messages.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName,Locale.ENGLISH);
		
		String value = bundle.getString("hi");
		System.out.println(value);
		
		
		
		
	}

}
