package cn.edu.hbue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hbue.util.AlertUtil;
import cn.edu.hbue.util.JDBCUtil;
import javafx.scene.control.Alert.AlertType;
/**
 * @author czqmike
 * 完成Ticket与MySQL间的数据库操作
 */
public class TicketDao {
	/*
	 * @return 返回ticket表中第一个bought为false的ticket的ticket_id
	 * 若不存在，则返回-1
	 */
	public static int selectTicketID(String train_number) {
		int ticket_id = -1;
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select ticket.ticket_id " + 
					 "from ticket " + 
					 "where ticket.train_number = ? AND ticket.bought = false";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, train_number);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				ticket_id = rs.getInt("ticket_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		return ticket_id;
	}
	/*
	 * 修改指定的ticket.bought为true
	 */
	public static boolean purchaseTicket(int ticket_id) {
		boolean done = true;
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "update ticketdb.ticket set bought = true where ticket_id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticket_id);
			
			stmt.executeUpdate();
			
			if (stmt.getUpdateCount() == -1) {
				done = false;
			}
		} catch (SQLException e) {
			AlertUtil.showAlert(AlertType.WARNING, "数据库操作异常！");
			done = false;
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		return done;
	}
	
	/*
	 * 修改指定的ticket.bought为false
	 */
	public static boolean refundTicket(int ticket_id) {
		boolean done = true;
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "update ticketdb.ticket set bought = false where ticket_id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticket_id);
			
			stmt.executeUpdate();
			
			if (stmt.getUpdateCount() == -1) {
				done = false;
			}
		} catch (SQLException e) {
			AlertUtil.showAlert(AlertType.WARNING, "数据库操作异常！");
			done = false;
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		return done;
	}
}
