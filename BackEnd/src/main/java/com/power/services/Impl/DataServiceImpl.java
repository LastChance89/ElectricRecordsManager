package com.power.services.Impl;

import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl 
//implements DataService
{

	/*
	 * @Autowired ClientDao userDao;
	 * 
	 * @Autowired RecordDao recordDao;
	 * 
	 * // private static String currentLine = null;
	 * 
	 * 
	 * public void initalizeNewUser(List<String> lines){
	 * setupNewUser(readUserFromCSV(lines), lines); }
	 * 
	 * public void setupNewUser(Client user, List<String> lines){ saveUser(user);
	 * saveRecords(readPowerData(lines,user.getAccountNumber())); }
	 * 
	 * 
	 * public void saveUser(Client user){ userDao.saveUser(user); }
	 * 
	 * public void saveRecords( List<Object[]> records){
	 * recordDao.savePowerRecords(records); }
	 * 
	 * public Client getUser(Map<String,String> inputMap){ return
	 * userDao.getUserData(inputMap); }
	 * 
	 * @Override public List<Record> getUserRecords(Long accNum){ return
	 * recordDao.getUserRecords(accNum); }
	 * 
	 * 
	 * 
	 * 
	 * //Move out these CSV methods into another class or back to utility?
	 * 
	 * public Client readUserFromCSV(List<String> lines) {
	 * 
	 * Client user = new Client(); for (String line : lines) { if
	 * (!line.split(",")[0].equals("TYPE")) { String[] currentReadInLine =
	 * line.split(",", 2); //we dont care about case so lets lower to ensure we get
	 * everything. switch(currentReadInLine[0].toLowerCase()){ case "name":
	 * user.setName(currentReadInLine[1]); break; case "address":
	 * user.setAddress(currentReadInLine[1].replaceAll("\"", "")); break; case
	 * "account number":
	 * user.setAccountNumber(Long.valueOf(currentReadInLine[1].toString())); break;
	 * case "service": user.setService(currentReadInLine[1]); default: // something
	 * bad got through we dont want. break; } } } return user;
	 * 
	 * }
	 * 
	 * public List<Object[]> readPowerData(List<String> lines, long accountNumber) {
	 * List<Object[]> powerUsage = new ArrayList<Object[]>(); for (String line :
	 * lines) { String[] currentReadInLine = line.split(","); if
	 * (currentReadInLine[0].equals("Electric usage")) {
	 *//**
		 * Because teh 8th column (Notes) is optional, we need to increase the array for
		 * the batch size. enters a null value which is fine.
		 *//*
			 * powerUsage.add(prepairData(currentReadInLine,accountNumber)); } } return
			 * powerUsage; }
			 * 
			 * //Have to massage the data so teh data is formatted properly for database
			 * insert. public Object [] prepairData(String [] currentReadInLine, long
			 * accountNumber){
			 * 
			 * ArrayList<Object> modifyArray = new ArrayList<Object>
			 * (CollectionUtils.arrayToList(currentReadInLine));
			 * 
			 * //Add the account number so we can link it up to the user account.
			 * if(modifyArray.size() != 8){ modifyArray.add(""); } modifyArray.add(0,
			 * accountNumber);
			 * 
			 * 
			 * //Remove the $. modifyArray.set(7,
			 * modifyArray.get(7).toString().replace("$",""));
			 * 
			 * return modifyArray.toArray(); }
			 * 
			 */

}
