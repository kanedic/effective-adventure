package kr.or.ddit.calc;

import java.io.Serializable;
import java.util.Objects;

/**		데이터를 전송하기 위해 사용됨
 * VO (ValueObject), DTO(DataTransferObject), JavaBean
 * 
 * 1. 값을 저장할 수 있는 property 정의.
 * 	property : java bean 규약에 따라 정의된 field
 * 2. peoperty 에 대해 캡슐화
 * 3. 캡슐화된 property 에 접근할 수 있는 메소드 제공 (getter / setter)
 * 		get[set]+propertyname==> camel case
 * 4. java bean 객체에 대한 (상태) 비교 방법 제공 : equals 메소드 재정의
 * 5. 상태를 확인할 수 있는 방법 제공 : toString 메소드 제정의
 * 6. 직렬화가 가능한 객체 표현
 * 
 */
public class CalculateVO implements Serializable {
	private double left;
	private double right;
	private OperatorType operator;
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public OperatorType getOperator() {
		return operator;
	}
	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	
	public double getResult() {
		return operator.operate(left, right);
	}
	
	public String getExpression() {
		return operator.getExpression(left, right);
	}
	@Override
	public int hashCode() {
		return Objects.hash(left, operator, right);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculateVO other = (CalculateVO) obj;
		return Double.doubleToLongBits(left) == Double.doubleToLongBits(other.left) && operator == other.operator
				&& Double.doubleToLongBits(right) == Double.doubleToLongBits(other.right);
	}
	@Override
	public String toString() {
		return "CalculateVO [left=" + left + ", right=" + right + ", operator=" + operator + "]";
	}
	
	
	
}
