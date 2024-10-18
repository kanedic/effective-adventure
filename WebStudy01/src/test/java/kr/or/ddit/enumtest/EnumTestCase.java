package kr.or.ddit.enumtest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;

import org.junit.jupiter.api.Test;

class EnumTestCase {
	enum OperatorType{//프라이빗으로 묶인 기본생성자
		PLUS('+',(l,r)->l+r),
		MINUS('-',(l,r)->l-r),
		MULTIPLY('*',(l,r)->l*r),
		DIVIDE('/',(l,r)->l/r);
		
		//한번 정한 enum은 절대불변 setter가 없음
		
		private OperatorType(char sign,DoubleBinaryOperator operator) {
			this.sign = sign;
			this.operator =  operator;
		}

		private DoubleBinaryOperator operator;
		
		
		private char sign;

		public char getSign() {
			return sign;
		}
		
	}

	@Test
	void test() {

	}

}
