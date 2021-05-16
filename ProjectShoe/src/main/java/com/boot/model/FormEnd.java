package com.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormEnd {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String fname;
	
	private String lname;
	
	private String city;
	
	private double phone;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "FormEnd [id=" + id + ", fname=" + fname + ", lname=" + lname + ", city=" + city + ", phone=" + phone
				+ "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getPhone() {
		return phone;
	}

	public void setPhone(double phone) {
		this.phone = phone;
	}

	public FormEnd(int id, String fname, String lname, String city, double phone) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.city = city;
		this.phone = phone;
	}

	public FormEnd() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
