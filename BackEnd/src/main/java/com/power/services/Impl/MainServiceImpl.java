package com.power.services.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.power.dao.ClientDao;
import com.power.dao.DashBoardDao;
import com.power.dao.GridMetaDAO;
import com.power.dao.RecordDao;
import com.power.dao.UserDao;
import com.power.messages.Message;
import com.power.models.Client;
import com.power.models.ClientReport;
import com.power.models.DashBoard;
import com.power.models.Record;
import com.power.services.MainService;
import com.power.util.ClientLoaderUtil;
import com.power.util.ResponseEntityUtil;

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
	public ResponseEntity<String> loadUserAndData(List<MultipartFile> files) {
		
		ResponseEntity<String> response = null;
		try {
			setupNewUser(ClientLoaderUtil.readInData(files));
			response = ResponseEntityUtil.createResponseMessage(HttpStatus.OK, Message.INITAL_LOAD_COMPLETE.getMessage());
		} catch (IOException e) {
			response = ResponseEntityUtil.InternalResponseError();
			logger.error("ERROR: ",e);
		} 
		catch(PersistenceException e) {
			response = ResponseEntityUtil.createResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR,"ERROR: " +e.getCause().getCause().toString().split(":")[1]);
			logger.error("ERROR ",e); 
		}
		catch (Exception e) { 
			logger.error("ERROR ",e); 
			response = ResponseEntityUtil.InternalResponseError();
		}
		
		return response; // ResponseEntityUtil.createResponseMessage(sucsess);
	}

	public ResponseEntity<String> userSearch(Map<String, String> inputMap) {
		try {
			List<Client> clients = clientDao.getSearchedUsersData(inputMap);
			return ResponseEntityUtil.createValidResponse(clients);
		}
		catch(Exception e) {
			return ResponseEntityUtil.InternalResponseError();
		}
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
	public ResponseEntity<String> getAllClients() {
		return ResponseEntityUtil.createValidResponse(clientDao.getAllUsers());
	}
	
	@Override
	public ResponseEntity<?> generateClientReport(long accNum) {
		try {
			ClientReport report = new ClientReport(); 
			report.setClient(clientDao.getClient(accNum));
			report.setRecords(recordDao.getClientRecords(accNum));
			return ResponseEntity.ok().body(report);
		}
		catch(Exception e) {
			return ResponseEntityUtil.InternalResponseError();
		}
	}
	
	public ResponseEntity<?> generateDashBoardData(){
		try {
			DashBoard dashboard = dashBoardDao.getDashboardData();
			return ResponseEntity.ok().body(dashboard);
		}
		catch(Exception e) {
			return ResponseEntityUtil.InternalResponseError();
		}
	}
}
