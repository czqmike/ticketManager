package cn.edu.hbue.model;

import java.time.LocalDateTime;

/**
 * @author czqmike
 * 对应ticketdb.refund中的一条数据
 */
public class Refund {
	private int ticket_id;
	private String username;
	private LocalDateTime time;
	private double price;
	
	public Refund() {
		this(0, "null", LocalDateTime.now(), 0.0);
	}
	public Refund(int ticket_id, String username, LocalDateTime time, double price) {
		this.ticket_id = ticket_id;
		this.username = username;
		this.time = time;
		this.price = price;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
