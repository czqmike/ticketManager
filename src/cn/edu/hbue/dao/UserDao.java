package cn.edu.hbue.dao;

import java.sql.*;

import cn.edu.hbue.model.User;
import cn.edu.hbue.util.JDBCUtil;
/**
 * @author czqmike
 * 完成User与MySQL间的数据库操作
 */
public class UserDao {
	public static User selectUser(String username) {
		User tu = new User();
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from user where username = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tu.setName(rs.getString("name"));
				tu.setUsername(rs.getString("username"));
				tu.setPassword(rs.getString("password"));
				tu.setPermission(rs.getString("permission"));
				tu.setPhonenumber(rs.getString("phonenumber"));
				tu.setCitizenID(rs.getString("citizenID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		
		return tu;
	}
	
	public static boolean insertIntoUser(User u) {
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "insert into user value(?, ?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getName());
			stmt.setString(4, u.getPermission());
			stmt.setString(5, u.getPhonenumber());
			stmt.setString(6, u.getCitizenID());
			
			stmt.executeUpdate();
			
			if (stmt.getUpdateCount() == -1) {
				done = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			done = false;
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		
		return done;
	}
}



























