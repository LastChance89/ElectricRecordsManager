package com.power.persistentData;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.global.message.Message;
import com.power.util.ResponseEntityUtil;

@Component
public class PersistentData{

	private Map<Integer,List<Map<String,String>>> gridMeta;

	public void setGridMeta(Map<Integer,List<Map<String,String>>> gridMeta) {
		this.gridMeta = gridMeta;
	} 
	
	//Return our metadata in a form the front end grid can read. 
	public ResponseEntity<String> getGridMeta(int gridID) {
		ResponseEntity<String> response; 
		if(!gridMeta.containsKey(gridID)) {
			response =	ResponseEntityUtil.createResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, Message.SERVER_ERROR.getMessage());
		}
		else {
			response = ResponseEntityUtil.createValidResponse(gridMeta.get(gridID));
		}
		return response;
	}
	
	
}
