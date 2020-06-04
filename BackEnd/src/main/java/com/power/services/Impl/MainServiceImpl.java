package com.power.services.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.power.dao.ClientDao;
import com.power.dao.DashBoardDao;
import com.power.dao.GridMetaDAO;
import com.power.dao.RecordDao;
import com.power.dao.UserDao;
import com.power.models.Client;
import com.power.models.DashBoard;
import com.power.models.Record;
import com.power.services.MainService;
import com.power.util.ClientLoaderUtil;

@Service
public class MainServiceImpl implements MainService {

	private Logger logger = LogManager.getLogger(MainServiceImpl.class);
	
	@Autowired
	ClientDao clientDao;

	@Autowired
	RecordDao recordDao;

	@Autowired
	UserDao userDao;
	
	@Autowired
	GridMetaDAO gridMetaDao;

	@Autowired
	DashBoardDao dashBoardDao;

	@Override
	// Method to load a user and their data through the first time.
	public boolean loadUserAndData(List<MultipartFile> files) {
		boolean sucsesfull = false;
		try {
			setupNewUser(ClientLoaderUtil.readInData(files));
		} catch (IOException e) {
			logger.error("ERROR: ",e);
		} catch (Exception e) { 
			logger.error("ERROR ",e);
		}
		sucsesfull = true;
		return sucsesfull;
	}

	public List<Client> getUserSearch(Map<String, String> inputMap) {
		List<Client> clientList = clientDao.getSearchedUsersData(inputMap);
		return clientList;
	}

	@Override
	public List<Map<String,Object>> getUserRecords(Long accNum) {
		return recordDao.getClientRecords(accNum);
	}

	@Override
	public Map<Integer, List<Map<String, String>>> getGridMeta() {
		return gridMetaDao.getGridMeta();
	}

	public void setupNewUser(Map<Client,List<Record>> clients) {
		for(Client client : clients.keySet()) {
				clientDao.saveUser(client);
				recordDao.saveClientRecords(clients.get(client));	
		}
	}

	@Override
	public List<SimpleGrantedAuthority> getRoles(String userName) {
		return userDao.getRoles(userName);
	}

	@Override
	public List<Client> getAllUsers() {
		return clientDao.getAllUsers();
	}
	
	@Override
	public DashBoard getDashboardData(){
		return dashBoardDao.getDashboardData();		
	}

}
