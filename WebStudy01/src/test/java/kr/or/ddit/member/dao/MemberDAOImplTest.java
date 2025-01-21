package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemberDAOImplTest {

		MemberDAO dao = new MemberDAOImpl();
	@Test
	void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectMember() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectMemberForAuth() {
		assertNotNull(dao.selectMemberForAuth("a001"));
		assertNull(dao.selectMemberForAuth("asdasdassdasdd"));
	}

	@Test
	void testSelectMemberList() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteMember() {
		fail("Not yet implemented");
	}

}
