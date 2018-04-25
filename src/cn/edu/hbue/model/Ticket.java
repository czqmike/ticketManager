package cn.edu.hbue.model;

/**
 * @author czqmike
 * 对应ticketdb.ticket中的一条数据
 */
public class Ticket {
	private int ticket_id;
	private String train_number, seat_number;
	private double price;
	private boolean bought;
	
	public Ticket() {
		this(0, "0", "0", 0.0, false);
	}
	public Ticket(int ticket_id, String train_number, String seat_number, double price, boolean bought) {
		this.ticket_id = ticket_id;
		this.train_number = train_number;
		this.seat_number = seat_number;
		this.price = price;
		this.bought = bought;
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTrain_number() {
		return train_number;
	}

	public void setTrain_number(String train_number) {
		this.train_number = train_number;
	}

	public String getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}
	
}
