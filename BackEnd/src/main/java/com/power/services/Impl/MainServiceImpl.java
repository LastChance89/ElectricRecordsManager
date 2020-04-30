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

import com.power.DAO.ClientDao;
import com.power.DAO.GridMetaDAO;
import com.power.DAO.RecordDao;
import com.power.DAO.UserDao;
import com.power.Util.ClientLoaderUtil;
import com.power.models.Client;
import com.power.models.Record;
import com.power.services.MainService;

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


	@Override
	// Method to load a user and their data through the first time.
	public boolean loadUserAndData(List<MultipartFile> files) {

		boolean sucsesfull = false;

		// for (MultipartFile file: files) {
		try {
			setupNewUser(ClientLoaderUtil.readInData(files));
		} catch (IOException e) {
			logger.error("ERROR: ",e);
		} catch (Exception e) { 
			logger.error("ERROR ",e);
		}
		sucsesfull = true;
		/// }
		return sucsesfull;
	}

	public List<Client> getUserSearch(Map<String, String> inputMap) {
		List<Client> clientList = clientDao.getSearchedUsersData(inputMap);
		return clientList;
	}

	@Override
	public void getUserPowerData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUserInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertRecordData() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Map<String, Object>> getUserRecords(Long accNum) {
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

}
