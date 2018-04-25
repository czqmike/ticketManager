package cn.edu.hbue.util;


import java.sql.*;

import javafx.scene.control.Alert.AlertType;
/**
 * @author czqmike
 * 包含数据库的连接与关闭
 */
public class JDBCUtil {
	static String username = "root";
	static String password = "123456";
	
	public static Connection getConn(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ticketdb?"
					+ "user=" + username + "&password=" + password;
			connection = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e1){
			AlertUtil.showAlert(AlertType.ERROR, "JDBC connection error");
		} catch (SQLException e) {
			AlertUtil.showAlert(AlertType.ERROR, "JDBC operation error");
		}
		return connection;
	}
	
	public static void closeConn(PreparedStatement preStmt, Connection conn){
		if (preStmt != null){
			try{
				conn.close();
			} catch (SQLException e){
				AlertUtil.showAlert(AlertType.ERROR, "SQL Close error");
			}
		}
		if (conn != null){
			try{
				conn.close();
			} catch (SQLException e){
				AlertUtil.showAlert(AlertType.ERROR, "SQL Close error");
			}
		}
	}
}

