package kr.or.ddit.props.exception;

public class PersonNotFoundException extends RuntimeException{

	public PersonNotFoundException(String id) {
		super(String.format("%s에 해당하는 person이 없음",id));
		
	}
	
}
