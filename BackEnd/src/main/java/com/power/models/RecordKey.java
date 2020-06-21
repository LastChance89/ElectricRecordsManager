package com.power.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

@Embeddable
public class RecordKey implements Serializable{
	
	@NotNull
	@Column(name = "account_number")
	public Long accountnumber;
	
	@NotNull
	@Column(name = "record_date")
	public Date recordDate; 
	
	@NotNull
	@Column(name = "start_time")
	public String startTime; 
	
	@NotNull
	@Column(name = "end_time")
	public String endTime;
	
	public RecordKey(Long accountNumber) {
		this.accountnumber = accountNumber;
	}
	
	public RecordKey(Long accountNumber, Date recordDate, String startTime, String endTime) {
		this.accountnumber = accountNumber;
		this.recordDate = recordDate;
		this.startTime = startTime; 
		this.endTime = endTime; 
	}
	
	
	public Long getAccountNumber() {
		return accountnumber;
	}
	public void setAccountNumber(Long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
