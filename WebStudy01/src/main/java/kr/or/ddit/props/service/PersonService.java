package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.exception.PersonNotFoundException;

/**
 * 
 * Business Logic Layer에 대한 접근 방법의 추상화
 * CRUD
 * 
 * 
 */
public interface PersonService {
	/**
	 * 등록
	 * @param person
	 */
	public boolean createPerson(PersonVO person);
	
	/**
	 * 단건 조회
	 * @param id 조회할 식별자
	 * @return 
	 * @throws PersonNotFoundException id에 해당하는 person이 존재하지 않으면 예외로 표현함.
	 */
	public PersonVO readPerson(String id) throws PersonNotFoundException;
	
	/**
	 * 다건 조회
	 * @param 없으면 empty list반환
	 */
	public List<PersonVO> readPersonList();
	/**
	 * 수정
	 * @param person
	 */
	public boolean modifyPerson(PersonVO person);
	/**
	 * 삭제
	 * @param id
	 */
	public boolean removePerson(String id);
}




















