package kr.or.ddit.props.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactoryCP;
import kr.or.ddit.props.PersonVO;

public class PersonDaoImpl implements PersonDAO {

	private static PersonDAO selfInstance;

	public static PersonDAO getInstance() {
		if (selfInstance == null) {
			selfInstance = new PersonDaoImpl();
		}
		return selfInstance;
	}
	private void personToQueryParameter(PersonVO person, PreparedStatement pstmt) throws SQLException {
		int idx = 1;

		pstmt.setString(idx++, person.getId());
		pstmt.setString(idx++, person.getName());
		pstmt.setString(idx++, person.getGender());
		pstmt.setString(idx++, person.getAge());
		pstmt.setString(idx++, person.getAddress());

	}
	private void personToQueryParameter2(PersonVO person, PreparedStatement pstmt) throws SQLException {
		int idx = 1;
		
		pstmt.setString(idx++, person.getName());
		pstmt.setString(idx++, person.getGender());
		pstmt.setString(idx++, person.getAge());
		pstmt.setString(idx++, person.getAddress());
		pstmt.setString(idx++, person.getId());
		
	}

	@Override
	public int insertPerson(PersonVO person) {
		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO PERSON (            ");
		sql.append("	ID,NAME,GENDER,AGE,ADDRESS ");
		sql.append(" ) VALUES (                       ");
		sql.append("	?,?,?,?,?                  ");
		sql.append(" )                              ");

		try (
				// 2.Connetcion 생성
				Connection conn = ConnectionFactoryCP.getConnection();
				// 3. 쿼리 객체 생성 : 쿼리가 미리 컴파일이 되있어야함
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 4.쿼리 파라미터 설정
			personToQueryParameter(person, pstmt);

			// 5.쿼리 실행
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public PersonVO selectPerson(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,name,gender,age,address from person where id= ? ");
		
		try (
				// 2.Connetcion 생성
				Connection conn = ConnectionFactoryCP.getConnection();
				// 3. 쿼리 객체 생성 : 쿼리가 미리 컴파일이 되있어야함
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 4.쿼리 파라미터 설정
			pstmt.setString(1, id);
			// 5.쿼리 실행
			ResultSet rs = pstmt.executeQuery();
			PersonVO person = null;
			if(rs.next()) {
				person = resultSetToPerson(rs,PersonVO.class);
			}
			
			return person;			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<PersonVO> selectPersonList() {
		List<PersonVO> list = new ArrayList<>();
		// 1.쿼리결정
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ID , NAME , GENDER , AGE FROM PERSON ");
		
		try (
				// 2.Connetcion 생성
				Connection conn = ConnectionFactoryCP.getConnection();
				// 3. 쿼리 객체 생성 : 쿼리가 미리 컴파일이 되있어야함
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 4.쿼리 파라미터 설정
			
			// 5.쿼리 실행
			ResultSet rs = pstmt.executeQuery();
			
		
			while(rs.next()) {
				list.add(resultSetToPerson(rs,PersonVO.class));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
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
			String propertyName = cn.toLowerCase();
			PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType);
			Method setter = pd.getWriteMethod();
			setter.invoke(instance, rs.getString(cn));
		}
		return instance;
		}catch(Exception e){
			throw new SQLException(e);
		}
		
//		PersonVO person = new PersonVO();
//		
//		if(columnNames.contains("ID")) {
//			person.setId(rs.getString("ID")	 );
//		}
//		
//		person.setName(rs.getString("name")	 );
//		person.setGender(rs.getString("gender"));
//		person.setAge(rs.getString("age")   );
//		
//		if(columnNames.contains("ADDRESS")) {
//			person.setAddress(rs.getString("ADDRESS")   );			
//		}
//		
//		return person;
	}
	
	@Override
	public int updatePerson(PersonVO person) {
		//1.쿼리결정
		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE PERSON    ");
		sql.append("SET              ");
		sql.append("    NAME = ?,    ");
		sql.append("    GENDER = ?,  ");
		sql.append("    AGE = ? ,    ");
		sql.append("    ADDRESS = ?  ");
		sql.append("WHERE            ");
		sql.append("        ID = ?   ");
		
		try (
				//2.Connetcion 생성
				Connection conn = ConnectionFactoryCP.getConnection();
				//3. 쿼리 객체 생성 : 쿼리가 미리 컴파일이 되있어야함
				//prepared는 미리 sql문을 컴파일을 켜놓고 데이터를 수정함
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			//4.쿼리 파라미터 설정
//			pstmt.setString(1, person.getName());
//			pstmt.setString(2, person.getAddress());
//			pstmt.setString(3, person.getGender());
//			pstmt.setString(4, person.getAge());
//			pstmt.setString(5, person.getId());
			
			personToQueryParameter2(person, pstmt);
			
			//5.쿼리 실행
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//아이디 확인 기능 ㅣㅆ으면 좋겟는데
	
	@Override
	public int deletePerson(String id) {
		//1.쿼리결정
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM PERSON WHERE ID=? ");
		
		try (
				//2.Connetcion 생성
				Connection conn = ConnectionFactoryCP.getConnection();
				//3. 쿼리 객체 생성 : 쿼리가 미리 컴파일이 되있어야함
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			//4.쿼리 파라미터 설정
			pstmt.setString(1, id);
			
			//5.쿼리 실행
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
