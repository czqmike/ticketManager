package cn.edu.hbue.model;

import java.time.LocalDateTime;

/**
 * @author czqmike
 * 对应ticketdb.order中的一条数据
 */
public class Order {
	private int ticket_id;
	private String username;
	private LocalDateTime time;
	private String station;
	
	public Order() {
	this(0, "null", LocalDateTime.now(), "null");	
	}
	public Order(int ticket_id, String username, LocalDateTime time, String station) {
		this.ticket_id = ticket_id;
		this.username = username;
		this.time = time;
		this.station = station;
	}
	
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
}
