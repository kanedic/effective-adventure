package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.WordUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.ConnectionFactoryCP;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public int insertMember(MemberVO member) {
		
		return 0;
	}

	@Override
	public MemberVO selectMember(String memId) {
		
		return null;
	}

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		//1.쿼리결정
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT MEM_ID,MEM_PASS,MEM_NAME,MEM_MAIL,MEM_HP FROM MEMBER WHERE MEM_ID = ?");
		
		try (
				//2.Connetcion 생성
				Connection conn = ConnectionFactoryCP.getConnection();
				//3. 쿼리 객체 생성 : 쿼리가 미리 컴파일이 되있어야함
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			//4.쿼리 파라미터 설정
			pstmt.setString(1, memId);
			//5.쿼리 실행
			ResultSet rs = pstmt.executeQuery();
			MemberVO saved = null;
			if(rs.next()) {
				saved= resultSetToPerson(rs, MemberVO.class);
				
//				saved.setMemId(rs.getString("MEM_ID"));
//				saved.setMemPass(rs.getString("MEM_PASS"));
//				saved.setMemName(rs.getString("MEM_NAME"));
//				saved.setMemMail(rs.getString("MEM_MAIL"));
//				saved.setMemHp(rs.getString("MEM_HP"));
			}
			
			return saved;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	private <T> T resultSetToPerson(ResultSet rs,Class<T> resultType) throws SQLException {
		try {
			
		ResultSetMetaData rsmd =  rs.getMetaData();
		
		int cnt = rsmd.getColumnCount();
		List<String> columnNames = new ArrayList<>(cnt);
		
		for(int i =1;i<=cnt;i++) {
			columnNames.add(rsmd.getColumnName(i)); //컬럼명 가져오기
		}
		
		//T타입의 객체를 만들어야함
		T instance = resultType.newInstance();//자동으로 생성자 실행 이게 new PersonVO의 역할 시행
		for(String cn :columnNames ) {
			
			String propertyName =WordUtils.capitalizeFully("A"+cn,'_').substring(1).replace("_", "");//프로퍼티 네임
			//여기에 스네이크 표기법 적용되서 오류ㅜ 뜸 그걸 바꿔야함
			PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType);
			Method setter = pd.getWriteMethod();
			setter.invoke(instance, rs.getString(cn));
		}
		return instance;
		}catch(Exception e){
			throw new SQLException(e);
		}

	}

	@Override
	public List<MemberVO> selectMemberList() {
		
		return null;
	}

	@Override
	public int updateMember(MemberVO member) {
		
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		
		return 0;
	}

}
