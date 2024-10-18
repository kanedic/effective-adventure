package kr.or.ddit.calc;

import java.io.Serializable;
import java.util.Objects;

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
