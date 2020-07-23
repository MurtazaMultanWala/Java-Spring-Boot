package com.Learning.JPA.JPAPractice.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*Take Class User and Map that into Table named User in Database*/

@Entity
public class User {
	
	@Id   //Primary Key
	@GeneratedValue  //Auto Generated
	private long id;
	
	private String name;
	private String role;
	
	protected User() {
		
	}
	
	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
	
}
