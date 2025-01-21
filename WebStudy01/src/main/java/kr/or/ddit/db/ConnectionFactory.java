package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Factory Object Pattern : 특정 객체의 생성을 전담하는 객체를 별도로 운영하는 설계 방식
 * 
 */
public class ConnectionFactory {
	private static ResourceBundle dbInfo;
//	private static Properties props;

	static {// 메모리에 이 클래스가 로딩 될 때 실행
		try {
			
			String baseName="kr.or.ddit.db.DBInfo";
			dbInfo=ResourceBundle.getBundle(baseName);
//			String driverClassName = dbInfo.getString("driverClassName");
			
//			String driverClassName = props.getProperty("driverClassName");

			Class.forName(dbInfo.getString("driverClassName")); // 1.메모리에 로딩
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() throws SQLException {

		String url = dbInfo.getString("url"); 
		String user = dbInfo.getString("user");
		String password = dbInfo.getString("password"); 
		
//		String url = props.getProperty("url");
//		String user = props.getProperty("user");
//		String password = props.getProperty("password");

		return DriverManager.getConnection(url, user, password);
	}

}
