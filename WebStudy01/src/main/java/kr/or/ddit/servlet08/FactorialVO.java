package kr.or.ddit.servlet08;

import java.io.Serializable;
import java.util.Objects;

//							이걸 승계해야 직렬화가 가능
public class FactorialVO implements Serializable{
	private int operand;
	private int result;
	private StringBuffer expression;
	public int getOperand() {
		return operand;
	}
	public void setOperand(int operand) {
		this.operand = operand;
		this.expression = new StringBuffer();
		this.result=reFactorial(operand, expression);
	}
	public int getResult() {
		return result;
	}
	public StringBuffer getExpression() {
		return expression;
	}
	@Override
	public int hashCode() {
		return Objects.hash(expression, operand, result);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactorialVO other = (FactorialVO) obj;
		return Objects.equals(expression, other.expression) && operand == other.operand && result == other.result;
	}
	
	@Override
	public String toString() {
		return "FactorialVO [operand=" + operand + ", result=" + result + ", expression=" + expression + "]";
	}
	
	int reFactorial(int num, StringBuffer expr) {
		if (num <= 0) {// 정상적인 파라미터가 아닐 때
			throw new IllegalArgumentException("팩토리얼 연산은 양의 정수를 대상으로 합니다");
		}

		if (num == 1) {// 종료 조건을 꼭 걸어줘야함
			expr.append(num);
			return 1;
		} else {
			expr.append(num + " * ");
			return num * reFactorial(num - 1, expr);
		}
	}
	
}
