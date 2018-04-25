package cn.edu.hbue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hbue.model.TicketCount;
import cn.edu.hbue.util.AlertUtil;
import cn.edu.hbue.util.JDBCUtil;
import javafx.scene.control.Alert.AlertType;
/**
 * @author czqmike
 * 完成TicketCount与MySQL间的数据库操作
 */
public class TicketCountDao {
	/*
	 * @return TicketCount
	 * 通过train_number选中一条ticket_count
	 */
	public static TicketCount SelectTicketCount(String train_number) {
		TicketCount tc = new TicketCount();
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from ticket_count where train_number = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, train_number);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				tc.setTrain_number(rs.getString("train_number"));
				tc.setTotal(rs.getInt("total"));
				tc.setRefund(rs.getInt("refund"));
				tc.setRemain(rs.getInt("remain"));
			}
		} catch (SQLException e) {
			AlertUtil.showAlert(AlertType.WARNING, "数据库操作异常！");
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		
		return tc;
	}
	
	/*
	 * @return boolean
	 * 当ticket.remain为0 或是 插入操作异常时，返回 false
	 * 否则，返回true
	 */
	public static boolean orderTicket(TicketCount ticket_count) {
		if (ticket_count.getRemain() == 0) {
			return false;
		}
		
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "update ticket_count set remain = ? where train_number = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticket_count.getRemain() - 1);
			stmt.setString(2, ticket_count.getTrain_number());
			
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
	 * @return boolean
	 * 退票后，应将剩余票数(ticket_count.remain)和退票数(ticket_count.refund) +1 
	 */
	public static boolean refundTicket(TicketCount ticket_count) {
		boolean done = true;
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		String sql = "update ticket_count set remain = ?, refund = ? where train_number = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticket_count.getRemain() + 1);
			stmt.setInt(2, ticket_count.getRefund() + 1);
			stmt.setString(3, ticket_count.getTrain_number());
			
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


















