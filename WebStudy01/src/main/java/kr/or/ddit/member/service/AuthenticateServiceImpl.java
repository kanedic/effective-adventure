package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	
	private MemberDAO dao = new MemberDAOImpl();
	@Override
	public MemberVO authenticate(MemberVO inputData) {
		
		MemberVO saved = dao.selectMemberForAuth(inputData.getMemId());
		if(saved!=null) {
			String savedPass = saved.getMemPass();
			String inputPass = inputData.getMemPass();
			if(savedPass.equals(inputPass)) {
				return saved;
			}
		}
		
		return null;
	}

}
