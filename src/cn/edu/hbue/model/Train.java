package cn.edu.hbue.model;

import java.time.*;
import cn.edu.hbue.util.DateUtil;
import javafx.beans.property.*;

/**
 * @author czqmike
 * 构造适合SelectDialog的数据结构
 * 为了输出方便，不与数据库中的Train一一对应
 * start_time:类型为LocalDateTime(由MySQL中的Date和Time转化而来)
 * time_cost:总共花费的分钟数
 */
public class Train {
	/*
	 * 本身的数据结构，为了让表格输出格式化的值另行创建两个StringProperty
	 * 来用于输出
	 */
	private StringProperty train_number;
	private StringProperty start_station;
	private StringProperty end_station;
	private LocalDateTime start_time;
	private int time_cost;
	private DoubleProperty price;
	
	private StringProperty start_time_property; //用于返回的标准格式时间的Property
	private StringProperty time_cost_property; //用于返回 "x时x分"的Property
	
	public Train() {
		this("0", "武汉", "武汉", LocalDateTime.parse("1970-01-01 00:00:00", DateUtil.dtf), 0, 0.0);		
	}
	public Train(String train_number, String start_station, String end_station, LocalDateTime start_time,
			int time_cost, double price) {
		this.train_number = new SimpleStringProperty(train_number);
		this.start_station = new SimpleStringProperty(start_station);
		this.end_station = new SimpleStringProperty(end_station);
		this.start_time = start_time;
		this.time_cost = time_cost;
		this.price = new SimpleDoubleProperty(price);
		
		this.start_time_property = new SimpleStringProperty(getStartTimeString(start_time));
		this.time_cost_property = new SimpleStringProperty(getTimeCostString(time_cost));
	}
	
	private String getTimeCostString(int minutes) {
		int h = 0, m = 0;
		h = minutes / 60;
		m = minutes % 60;
		String ret = Integer.toString(h) + "时" + Integer.toString(m) + "分";
		return ret;
	}
	private String getStartTimeString(LocalDateTime start_time) {		
		return start_time.format(DateUtil.dtf);
	}
	
	public String getTrain_number() {
		return train_number.get();
	}
	public void setTrain_number(String train_number) {
		this.train_number.set(train_number);
	}
	public StringProperty getTrainNumberProperty() {
		return train_number;
	}
	
	public String getStart_station() {
		return start_station.get();
	}
	public void setStart_station(String start_station) {
		this.start_station.set(start_station);
	}
	public StringProperty getStartStationProperty() {
		return start_station;
	}
	
	public String getEnd_station() {
		return end_station.get();
	}
	public void setEnd_station(String end_station) {
		this.end_station.set(end_station);
	}
	public StringProperty getEndStationProperty() {
		return end_station;
	}
	
	public LocalDateTime getStart_time() {
		return start_time;
	}
	/*
	 * 设定类本身的start_time时也要同时改变start_time_property
	 */
	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
		start_time_property.setValue(getStartTimeString(start_time));
	}
	/*
	 * 返回另行构造的StringProperty而不是start_time
	 */
	public StringProperty getStartTimeProperty() {
		return start_time_property;
	}
	
	public int getTime_cost() {
		return time_cost;
	}
	/*
	 * 设定类本身的time_cost时也要同时改变time_cost_property
	 */
	public void setTime_cost(int time_cost) {
		this.time_cost = time_cost;
		this.time_cost_property.setValue(getTimeCostString(time_cost));
	}
	/*
	 * 返回另行构造的StringProperty而不是time_cost
	 */
	public StringProperty getTimeCostProperty() {
		
		return time_cost_property;
	}
	
	public double getPrice() {
		return price.get();
	}
	public void setPrice(double price) {
		this.price.set(price);
	}
	public DoubleProperty getPriceProperty() {
		return price;
	}
	
}
