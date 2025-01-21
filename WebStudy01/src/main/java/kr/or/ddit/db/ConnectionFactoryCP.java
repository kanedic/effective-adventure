package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Factory Object Pattern : 특정 객체의 생성을 전담하는 객체를 별도로 운영하는 설계 방식
 * 
 */
public class ConnectionFactoryCP {
	private static ResourceBundle dbInfo;
	private static DataSource ds;
//	private static Properties props;

	static {// 메모리에 이 클래스가 로딩 될 때 실행
		String baseName="kr.or.ddit.db.DBInfo";
		dbInfo=ResourceBundle.getBundle(baseName);

		HikariConfig config = new HikariConfig();
		config.setDriverClassName(dbInfo.getString("driverClassName"));
		config.setJdbcUrl(dbInfo.getString("url"));
		config.setUsername(dbInfo.getString("user"));
		config.setPassword(dbInfo.getString("password"));
		
		//pooling 정책 설정
		config.setConnectionTestQuery(dbInfo.getString("testQuery"));//쿼리문 테스트
		config.setMinimumIdle(Integer.parseInt(dbInfo.getString("minimulIdle")));//생성과 동시에 [3] 개의 커넥션을 미리 수립하겠다는 뜻
		config.setMaximumPoolSize(Integer.parseInt(dbInfo.getString("maximulSize")));// 3개가 부족할 때, 최대 [5] 개의 커넥션까지 수립 하겠다는 뜻
		config.setConnectionTimeout(Long.parseLong(dbInfo.getString("maxWait")));//2초동안 스레드[요청]를 살려놓음
		
		ds = new HikariDataSource(config); //이걸 생성하면서 이미 커넥션이 수립됨.
		
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();

	}

}












