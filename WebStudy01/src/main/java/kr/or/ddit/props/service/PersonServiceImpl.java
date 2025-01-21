package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.dao.PersonDAO;
import kr.or.ddit.props.dao.PersonDaoImpl;
import kr.or.ddit.props.dao.PropertiesFilePersonDAOImpl;
import kr.or.ddit.props.exception.PersonNotFoundException;

public class PersonServiceImpl implements PersonService {

	private PersonDAO dao = new PersonDaoImpl();
	
	private static PersonService instance;
	private PersonServiceImpl() {
			super();
	}

	public static PersonService getInstance() {
		if(instance==null) {
			instance = new PersonServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public boolean createPerson(PersonVO person) {
		
		return dao.insertPerson(person) > 0;
	}

	@Override
	public PersonVO readPerson(String id) throws PersonNotFoundException {
		PersonVO person = dao.selectPerson(id);
		
		if(person==null) {
			throw new PersonNotFoundException(id);
		}
		
		return person;
	}

	@Override
	public List<PersonVO> readPersonList() {
		
		return dao.selectPersonList();
	}

	@Override
	public boolean modifyPerson(PersonVO person) {
		
		return dao.updatePerson(person) > 0;
	}

	@Override
	public boolean removePerson(String id) {
		
		return  dao.deletePerson(id) > 0;
	}

}
