package kr.or.ddit.props.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.props.PersonVO;

class PersonDaoImplTest {
	
	PersonDAO dao = new PersonDaoImpl();
	PersonDAO fileDAO = PropertiesFilePersonDAOImpl.getInstance();
	
	@Test
	void testInsertPerson() {
//		System.out.println(fileDAO.selectPersonList().size());
//	fileDAO.sel
		
		fileDAO.selectPersonList().forEach(p->dao.insertPerson(p));
//		
	}

	@Test
	void testSelectPerson() {
		assertNotNull(dao.selectPerson("C0012"));
		System.out.println(dao.selectPerson("C0012"));
		assertNull(dao.selectPerson("awdawdawdawd"));
	}

	@Test
	void testSelectPersonList() {
		dao.selectPersonList().forEach(System.out::println);
	}

	@Test
	void testUpdatePerson() {
		
		PersonVO person = new PersonVO();
		PersonVO person2 = new PersonVO();
		String test = "test";
		String testId= "aasas";
		
		person.setId(testId);
		person.setName(test);
		person.setGender("t");
		person.setAddress(test);
		person.setAge("98");
		
		person2.setId("asdasdasd");
		person2.setName(test);
		person2.setGender("t");
		person2.setAddress(test);
		person2.setAge("98");
		
		assertDoesNotThrow(()->{
			PersonVO person3 = dao.selectPerson(testId);
			person3.setAddress("asdasdasdsadsad");
			int gg= dao.updatePerson(person3);			
			assertEquals(1,gg);
		});

		
		//		int gg= dao.updatePerson(person2);
		//assertEqueas = 설정 값과 결과값이 같으면 일치 테스트 성공 
		//excuteUpdate 들의 결과값은 숫자로 표시되니 이를 적절히 사용
//		assertEquals(1,dao.updatePerson(person));
//		assertEquals(0, gg);
		
		
		
	}

	@Test
	void testDeletePerson() {
		String id="dddd";
		
		assertDoesNotThrow(()->{
			int gg=dao.deletePerson(id);
			assertEquals(1, gg);
		});
		
	}

}










