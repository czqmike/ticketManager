package cn.edu.hbue.model;
/**
 * @author czqmike
 * 对应ticketdb.user中的一条数据
 */
public class User {
	private String username;
	private String password;
	private String name;
	private String permission;
	private String phonenumber;
	private String citizenID;
	
	/*
	 * permission无法在构造方法中预设，必须通过set方法手动修改。
	 */
	public User() {
		this.username = "0";
		this.password = "0";
		this.permission = "user";
		this.phonenumber = "0";
		this.citizenID = "0";
	}
	public User(String username, String password, String name, String phonenumber,
			String citizenID) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.permission = "user";
		this.phonenumber = phonenumber;
		this.citizenID = citizenID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCitizenID() {
		return citizenID;
	}
	public void setCitizenID(String citizenID) {
		this.citizenID = citizenID;
	}
	
}
