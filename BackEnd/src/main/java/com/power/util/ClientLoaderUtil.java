package com.power.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.power.models.Client;
import com.power.models.Record;
import com.power.models.RecordKey;


public class ClientLoaderUtil {
	private static Logger logger = LogManager.getLogger(ClientLoaderUtil.class);
	private static Client client; 
	private static Map<String,Integer> colMap;
	private static Map<Client,List<Record>> userRecordMapping;
	private static List<Record> clientRecords;
	
	
	public static Map<Client,List<Record>> readInData(List<MultipartFile> files) throws IOException {
	
		BufferedReader br = null;
		InputStreamReader input = null;
		userRecordMapping = new HashMap<Client,List<Record>>(); 
		
		try {
		for(MultipartFile mFile: files) {
				logger.info("Reading in file" + mFile.getResource().getFilename());
			
				input  = new InputStreamReader(mFile.getInputStream());
				
				br = new BufferedReader(input);
				
				String currentLine;
				client = new Client();
				clientRecords = new ArrayList<Record>();
			    colMap = new HashMap<String,Integer>();
				
				while ((currentLine = br.readLine()) != null) {
					String[] currentLineColumns = currentLine.split(",");
					if(currentLineColumns.length == 2 || currentLineColumns[0].equals("Address")) {
						//Required additional split logic because of address field that has additional comma. 
						currentLineColumns = currentLine.split(",",2);
						setupUser(currentLineColumns);
					}
					// Start record field processing. 
					else if(currentLineColumns.length >2) {
						setupClientRecords(currentLineColumns);
					}
				}
				userRecordMapping.put(client, clientRecords);
			} 
		}
		catch(Exception e) {
			logger.error("ERROR: ",e);
		}
		finally {
			if (br != null) {
				logger.debug("Closing buffered reader");
				br.close();
			}
			if(input != null) {
				input.close();
			}
		}
		return userRecordMapping;
	}
	
	private static void setupUser(String[] currentLineColumns) {
		switch (currentLineColumns[0].toLowerCase()) {
			case "name":
				client.setName(currentLineColumns[1]);
				break;
			case "address":
				client.setAddress(currentLineColumns[1].replaceAll("\"", ""));
				break;
			case "account number":
				client.setAccountNumber(Long.valueOf(currentLineColumns[1].toString()));
				break;
			case "service":
				client.setService(currentLineColumns[1]);
				break;
			default:
				break;
		}	
	}
	
	
	private static void setupClientRecords(String[] currentLineColumns ) throws ParseException {
		/*
		 * This sets up the index for each column taht we will use to 
		 * map to the client object.  
		 */
		if(currentLineColumns[0].equals("TYPE")) {
			for(int i = 0; i<currentLineColumns.length; i++) {
				colMap.put(currentLineColumns[i], i);
			}
		}
		else {		
			//Not every record has a note, so we just set it to an empty string if one does not exist. 
			String note = currentLineColumns.length != 8 ? "" : currentLineColumns[colMap.get("NOTES")];
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			//This is the composite   key for each record. 
			RecordKey key = new RecordKey(
					client.getAccountnumber(),
					format.parse(currentLineColumns[colMap.get("DATE")]),
					currentLineColumns[colMap.get("START TIME")],
					currentLineColumns[colMap.get("END TIME")]
					);
			
			Record record = new Record(key, 
					currentLineColumns[colMap.get("TYPE")],
					Double.valueOf(currentLineColumns[colMap.get("USAGE")]),
					currentLineColumns[colMap.get("UNITS")],
					Double.valueOf(currentLineColumns[colMap.get("COST")].replace("$", "")),
					note
					);
			clientRecords.add(record);
		}
	}
	
}
