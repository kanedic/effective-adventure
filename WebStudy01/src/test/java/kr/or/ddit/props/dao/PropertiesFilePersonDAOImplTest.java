package kr.or.ddit.props.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.props.PersonVO;

class PropertiesFilePersonDAOImplTest {

	PersonDAO dao = PropertiesFilePersonDAOImpl.getInstance();
	
	@Test
	void testInsertPerson() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectPerson() {
		PersonVO person = new PersonVO();
		person.setId("dummy01");
		person.setName("이름");
		person.setGender("M");
		person.setAge("23");
		person.setAddress("구서 전대"); 
		
		assertDoesNotThrow(()->{
			int rowcnt = dao.insertPerson(person);
			assertEquals(1, rowcnt);
		});
		
	}

	@Test
	void testSelectPersonList() {
		dao.selectPersonList().forEach(System.out::println);
	}

	@Test
	void testUpdatePerson() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletePerson() {
		fail("Not yet implemented");
	}

}
