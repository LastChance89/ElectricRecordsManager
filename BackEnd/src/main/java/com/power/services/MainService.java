package com.power.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import com.power.models.Client;
import com.power.models.ClientReport;
import com.power.models.DashBoard;

public interface MainService {
	//public Client getUserInfo(Map<String,String> inputMap); //Repurpose me. 
	public  ResponseEntity<Boolean>  loadUserAndData( List<MultipartFile> file);
	public List<SimpleGrantedAuthority> getRoles(String userName);
	public Map<Integer,List<Map<String,String>>> getGridMeta();
	public ResponseEntity<String>  getAllClients();
	public ResponseEntity<String> userSearch(Map<String,String> inputMap);
	public ResponseEntity<?> generateDashBoardData();
	public ResponseEntity<?> generateClientReport(long accNum);
	
}
