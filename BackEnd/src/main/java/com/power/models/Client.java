package com.power.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="Client")
public class Client implements Serializable{

	@Id
	@Column(name="ACCOUNT_NUMBER")
	private long accountNumber;
	
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="SERVICE")
	private String service;
	
	public Client() {
		
	}
	
	public Client(String name, String address, long accountNumber, String service) {
		this.name = name; 
		this.address = address; 
		this.accountNumber = accountNumber; 
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
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	
}
