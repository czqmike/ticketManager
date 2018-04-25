package cn.edu.hbue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cn.edu.hbue.model.Refund;
import cn.edu.hbue.util.DateUtil;
import cn.edu.hbue.util.JDBCUtil;
/**
 * @author czqmike
 * 完成Refund与MySQL间的数据库操作
 */
public class RefundDao {
	public static boolean insertIntoRefund(Refund refund) {
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "insert into ticketdb.refund(ticket_id, username, time, price) values(?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, refund.getTicket_id());
			stmt.setString(2, refund.getUsername());
			stmt.setString(3, refund.getTime().format(DateUtil.dtf));
			stmt.setDouble(4, refund.getPrice());
			
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
	
	public static boolean deleteRefund(int ticket_id, String username) {
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from ticketdb.refund where ticket_id = ? AND username = ?";
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
