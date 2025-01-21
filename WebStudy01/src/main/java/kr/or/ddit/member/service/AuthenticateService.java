package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

/**
 * 인증 시스템 용 business logic layer
 * @author PC_02
 *
 */
public interface AuthenticateService {
	
	/**
	 * 인증 여부 판단 
	 * @param inputData : 입력된 아이디와 비밀번호를 담은 Vo객체
	 * @return 인증성공시 인증된 사용자의 나머지 정보를 가진 객체 반환
	 * 		   인증실패시, null 반환 (추후 변경)
	 */
   public MemberVO authenticate(MemberVO inputData);


}
