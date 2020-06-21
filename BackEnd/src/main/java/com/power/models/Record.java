package com.power.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RECORDS")
public class Record {
	
	//our composite key. 
	@Id
	private RecordKey recordKey;
	
	@Column(name="TYPE")
	private String type; 
	@Column(name = "POWER_USAGE")
	private Double powerUsage;
	@Column(name="UNITS")
	private String units;
	@Column(name="COST")
	private Double cost;
	@Column(name="NOTES")
	private String note;
	
	public Record() {	
	}
	
	public Record(RecordKey recordKey, String type, Double powerUsage, String units, Double cost, String note) {
		this.recordKey = recordKey;
		this.type = type; 
		this.powerUsage = powerUsage;
		this.units = units; 
		this.cost = cost;
		this.note = note;
	}
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Double getPower_usage() {
		return powerUsage;
	}
	public void setPower_usage(Double power_usage) {
		this.powerUsage = power_usage;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


	public RecordKey getRecordKey() {
		return recordKey;
	}

	public void setRecordKey(RecordKey recordKey) {
		this.recordKey = recordKey;
	}
	
}
