package com.ecommerce.model;

public class User {

	private String userName;
	private String email;
	private String password;
	private String address;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String userName,String email,String password,String address){
		this.userName=userName;
		this.email=email;
		this.password=password;
		this.address=address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return userName+"    "+email+"     "+password+"      "+address;
	}
}
