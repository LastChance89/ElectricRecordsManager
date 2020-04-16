package com.power.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class Record {
	
	//our composite key. 
	@Id
	private RecordKey recordKey;
	
	@Column(name="type")
	public String type; 
	@Column(name = "power_usage")
	public Double powerUsage;
	@Column(name="units")
	public String units;
	@Column(name="cost")
	public Double cost;
	@Column(name="notes")
	public String note;
	

	
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
