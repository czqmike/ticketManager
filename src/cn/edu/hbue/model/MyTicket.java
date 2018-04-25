package cn.edu.hbue.model;

import java.time.LocalDateTime;

import cn.edu.hbue.util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author czqmike
 * Train的子类，构造适合MyTicketDialog的数据结构
 */
public class MyTicket extends Train{
	private IntegerProperty ticket_id;
	private StringProperty seat_number;
	public MyTicket() {
		this("0", "武汉", "武汉", LocalDateTime.parse("1970-01-01 00:00:00", DateUtil.dtf), 0, 0.0, 0, "0");	
	}
	public MyTicket(String train_number, String start_station, String end_station, LocalDateTime start_time,
			int time_cost, double price, int ticket_id, String seat_number) {
		super(train_number, start_station, end_station, start_time, time_cost, price);
		// TODO Auto-generated constructor stub
		this.ticket_id = new SimpleIntegerProperty(ticket_id);
		this.seat_number = new SimpleStringProperty(seat_number);
	}
	
	public int getTicket_id() {
		return ticket_id.get();
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id.set(ticket_id);
	}
	public IntegerProperty getTicketIDProperty() {
		return ticket_id;
	}
	
	public String getSeat_number() {
		return seat_number.get();
	}
	public void setSeat_number(String seat_number) {
		this.seat_number.set(seat_number);
	}
	public StringProperty getSeatNumberProperty() {
		return seat_number;
	}
	
}












