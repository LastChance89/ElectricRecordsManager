package com.power.persistentData;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.power.models.GridMeta;

@Component
public class PersistentData{

	private Map<Integer,List<Map<String,String>>> gridMeta;

	public void setGridMeta(Map<Integer,List<Map<String,String>>> gridMeta) {
		this.gridMeta = gridMeta;
	} 
	
	//Return our metadata in a form the front end grid can read. 
	public List<Map<String,String>> getGridMeta(int gridID) {
		return gridMeta.get(gridID);
	}
	
	
}
