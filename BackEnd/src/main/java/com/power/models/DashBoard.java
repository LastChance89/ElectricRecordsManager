package com.power.models;

import java.util.List;

public class DashBoard {

	private long clientCount;
	private long recordCount;
	private List<String> dateRange;
	
	public  DashBoard(long clientCount, long recordCount, List<String> dateRange) {
		this.clientCount = clientCount; 
		this.recordCount = recordCount; 
		this.dateRange = dateRange;
	}
	
	public long getClientCount() {
		return clientCount;
	}
	public void setClientCount(long clientCount) {
		this.clientCount = clientCount;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public List<String> getDateRange() {
		return dateRange;
	}
	public void setDateRange(List<String> dateRange) {
		this.dateRange = dateRange;
	} 
	
	
	
	
}
