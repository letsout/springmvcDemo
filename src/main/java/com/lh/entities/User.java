package com.lh.entities;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String age;
	private  Address address;
	
	public User(String username, String password, String email, String age) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
	}
	
	public User(String username, String password, String email, String age, Address address) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.address = address;
	}

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String email, String age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", age=" + age
				+ ", address=" + address + "]";
	}
	
}
