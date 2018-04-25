package cn.edu.hbue.model;

/**
 * @author czqmike
 * 对应ticketdb.count中的一条数据
 */
public class TicketCount {
	private String train_number;
	private int total, refund, remain;
	
	public TicketCount() {
		this("0", 0, 0, 0);
	}
	public TicketCount(String train_number, int total, int refund, int remain) {
		this.train_number = train_number;
		this.total = total;
		this.refund = refund;
		this.remain = remain;
	}
	
	public String getTrain_number() {
		return train_number;
	}
	public void setTrain_number(String train_number) {
		this.train_number = train_number;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRefund() {
		return refund;
	}
	public void setRefund(int refund) {
		this.refund = refund;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}

}
