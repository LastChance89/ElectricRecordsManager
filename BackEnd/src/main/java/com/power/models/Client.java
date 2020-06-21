package com.power.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CLIENT")
public class Client implements Serializable{

	@Id
	@Column(name="ACCOUNT_NUMBER")
	private long accountnumber;
	
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="SERVICE")
	private String service;
	
	public Client() {
		
	}
	
	public Client(String name, String address, long accountnumber, String service) {
		this.name = name; 
		this.address = address; 
		this.accountnumber = accountnumber; 
		this.service = service;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountNumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	
}
