package cn.edu.hbue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import cn.edu.hbue.model.Train;
import cn.edu.hbue.util.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author czqmike
 * @return ObservableList<Train> 
 * 在train表和ticket表中查找始发站为start_statino, 终点站为end_station, 
 * 发车日期为start_time的train, 并且填充到这个List中, 以方便用表格显示数据。
 */
public class TrainDao{
	
	public static ObservableList<Train> SelectTrain(String start_station, String end_station, LocalDate start_time) {
		ObservableList<Train> data = FXCollections.observableArrayList();
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select train.train_number, start_station, end_station, start_date, start_time, timecost, price " + //注意空格
					 "from train, ticket " + 
					 "where train.train_number = ticket.train_number AND start_station = ? AND end_station = ? AND start_date = ? " + 
					 "group by train.train_number";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, start_station);
			stmt.setString(2, end_station);
			stmt.setString(3, start_time.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				Train t = new Train();
				t.setTrain_number(rs.getString("train_number"));
				t.setStart_station(rs.getString("start_station"));
				t.setEnd_station(rs.getString("end_station"));
				//将从SQL中获取的 Date 和 Time 合并为一个LocalDateTime
				t.setStart_time(LocalDateTime.of
						(rs.getDate("start_date").toLocalDate(), rs.getTime("start_time").toLocalTime()));
				t.setTime_cost(rs.getInt("timecost"));//MySQL中这一列为"timecost"而不是"time_cost"
				t.setPrice(rs.getDouble("price"));
				
				data.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		
		return data;
	}
	
	/*
	 * 通过车次号查询列车
	 */
	public static ObservableList<Train> SelectByTrainNumber(String train_number) {
		ObservableList<Train> data = FXCollections.observableArrayList();
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select train.train_number, start_station, end_station, start_date, start_time, timecost, price " + //注意空格
					 "from train, ticket " + 
					 "where train.train_number = ticket.train_number AND train.train_number = ? " + 
					 "group by train.train_number ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, train_number);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Train t = new Train();
				t.setTrain_number(rs.getString("train_number"));
				t.setStart_station(rs.getString("start_station"));
				t.setEnd_station(rs.getString("end_station"));
				//将从SQL中获取的 Date 和 Time 合并为一个LocalDateTime
				t.setStart_time(LocalDateTime.of
						(rs.getDate("start_date").toLocalDate(), rs.getTime("start_time").toLocalTime()));
				t.setTime_cost(rs.getInt("timecost"));//MySQL中这一列为"timecost"而不是"time_cost"
				t.setPrice(rs.getDouble("price"));
				
				data.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		
		return data;
	}
	
	/*
	 * 通过车票号查询列车
	 */
	public static ObservableList<Train> SelectByTicketId(int ticket_id) {
		ObservableList<Train> data = FXCollections.observableArrayList();
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select train.train_number, start_station, end_station, start_date, start_time, timecost, price " + //注意空格
					 "from train, ticket " + 
					 "where train.train_number = ticket.train_number AND ticket.ticket_id = ? " + 
					 "group by train.train_number ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticket_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Train t = new Train();
				t.setTrain_number(rs.getString("train_number"));
				t.setStart_station(rs.getString("start_station"));
				t.setEnd_station(rs.getString("end_station"));
				//将从SQL中获取的 Date 和 Time 合并为一个LocalDateTime
				t.setStart_time(LocalDateTime.of
						(rs.getDate("start_date").toLocalDate(), rs.getTime("start_time").toLocalTime()));
				t.setTime_cost(rs.getInt("timecost"));//MySQL中这一列为"timecost"而不是"time_cost"
				t.setPrice(rs.getDouble("price"));
				
				data.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(stmt, conn);
		}
		
		return data;
	}
}
