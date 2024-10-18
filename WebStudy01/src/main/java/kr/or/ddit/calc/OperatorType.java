package kr.or.ddit.calc;

import java.util.function.DoubleBinaryOperator;

public enum OperatorType{//프라이빗으로 묶인 기본생성자
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
	
	public double operate(double left , double right) {
		return operator.applyAsDouble(left, right);
	}
	
	//HCLC (High Cohesion Loose Coupling)
	//	 응집력을 높이고 (책임을 모으고) 결합력을 낮추어라	(모듈과 모듈은 서로 종속되면 안됨)
	
	public String getExpression(double left , double right) {
		
		return String.format("%f %c %f = %f", left,sign,right,operate(left,right));
	}
	
}