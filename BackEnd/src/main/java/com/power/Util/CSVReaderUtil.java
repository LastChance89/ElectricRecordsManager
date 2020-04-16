package com.power.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


public class CSVReaderUtil {
	private static Logger logger = LogManager.getLogger(CSVReaderUtil.class);
	
	public static Map<Client,List<Record>> readInData(List<MultipartFile> files) throws IOException {
		
	
		
		BufferedReader br = null;
		FileReader fr = null;

		Map<Client,List<Record>> userRecordMapping = new HashMap<Client,List<Record>>(); 
		
		try {
		for(MultipartFile mFile: files) {
				logger.info("Reading in file" + mFile.getName());
			
				Client client = new Client();
				List<Record> clientRecords = new ArrayList<Record>();
				br = new BufferedReader(new InputStreamReader(mFile.getInputStream()));
				String currentLine;
				Map<String,Integer> colMap = new HashMap<String,Integer>();
				while ((currentLine = br.readLine()) != null) {
					String[] currentLineColumns = currentLine.split(",");
					// have to do a custom string split for the user lines, so we check if split is 2 
					//of if the first index is Address, in which we need to do another split. 
					if(currentLineColumns.length == 2 || currentLineColumns[0].equals("Address")) { // user information are columns only
						currentLineColumns = currentLine.split(",",2);
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
							default:
								break;
						}
					}
					else if(currentLineColumns.length >2) {
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
									client.getAccountNumber(),
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
			if (fr != null) {
				logger.debug("Closing file reader");
				fr.close();
			}
		}
		return userRecordMapping;
	}
}
