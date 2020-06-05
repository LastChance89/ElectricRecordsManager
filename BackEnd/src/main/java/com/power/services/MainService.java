package com.power.services;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import com.power.models.Client;
import com.power.models.DashBoard;
import com.power.models.Record;

public interface MainService {
	//public Client getUserInfo(Map<String,String> inputMap); //Repurpose me. 
	public boolean loadUserAndData( List<MultipartFile> file);
	public List<Map<String,Object>> getUserRecords(Long accNum);
	public List<SimpleGrantedAuthority> getRoles(String userName);
	public Map<Integer,List<Map<String,String>>> getGridMeta();
	public List<Client> getAllUsers();
	public List<Client> userSearch(Map<String,String> inputMap);
	public DashBoard getDashboardData();
	public Client getClient(long accNum);
}
