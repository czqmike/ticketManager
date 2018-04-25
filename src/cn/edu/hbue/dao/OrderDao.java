package cn.edu.hbue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import cn.edu.hbue.model.Order;
import cn.edu.hbue.util.JDBCUtil;

/**
 * @author czqmike
 * 完成Order与MySQL间的数据库操作
 */
public class OrderDao {
	public static boolean insertIntoOrder(Order order) {
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "insert into ticketdb.order(ticket_id, username, time, station) values(?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, order.getTicket_id());
			stmt.setString(2, order.getUsername());
			stmt.setString(3, order.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			stmt.setString(4, order.getStation());
			
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
	
	public static boolean deleteOrder(int ticket_id, String username) {
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from ticketdb.order where ticket_id = ? AND username = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticket_id);
			stmt.setString(2, username);
			
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
