package cn.edu.hbue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import cn.edu.hbue.model.MyTicket;
import cn.edu.hbue.util.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author czqmike
 * 完成MyTicket与MySQL间的数据库操作
 */
public class MyTicketDao {
	/*
	 * @return ObervableList<MyTicket>
	 * 通过username查询这个用户的票务相关信息并填充到ObservableList里
	 */
	public static ObservableList<MyTicket> SelectMyTicket(String username) {
		ObservableList<MyTicket> data = FXCollections.observableArrayList();
		
		Connection conn = JDBCUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select train.train_number, ticket_id, start_station, end_station, start_date, start_time, timecost, price, seat_number " + 
					 "from train, ticket " + 
					 "where train.train_number = ticket.train_number AND ticket_id IN " + 
					 "	(select ticket_id " + 
					 "	from ticketdb.order " + 
					 "	where username = ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				MyTicket t = new MyTicket();
				t.setTrain_number(rs.getString("train_number"));
				t.setTicket_id(rs.getInt("ticket_id"));
				t.setStart_station(rs.getString("start_station"));
				t.setEnd_station(rs.getString("end_station"));
				//将从SQL中获取的 Date 和 Time 合并为一个LocalDateTime
				t.setStart_time(LocalDateTime.of
						(rs.getDate("start_date").toLocalDate(), rs.getTime("start_time").toLocalTime()));
				t.setTime_cost(rs.getInt("timecost"));//MySQL中这一列为"timecost"而不是"time_cost"
				t.setPrice(rs.getDouble("price"));
				t.setSeat_number(rs.getString("seat_number"));
				
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
