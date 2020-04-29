package com.power.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.power.Util.ClientLoaderUtil;
import com.power.models.Client;
import com.power.models.Record;

@RunWith(PowerMockRunner.class)
public class ClientLoaderUtilTest {

	@Mock
	private BufferedReader br;
	
	private StringBuilder sb;
	
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@InjectMocks
	private ClientLoaderUtil clientLoaderUtil;
	
	@Before
	public void setUp() throws Exception {
		sb = new StringBuilder("Name,Guy 2")
		.append("\n")
		.append("Address,\"222 way Drive, Somewhere1 MD\"")
		.append("\n")
		.append("Account Number,55414114")
		.append("\n")
		.append("Service,Service 1")
		.append("\n")
		.append("TYPE,DATE,START TIME,END TIME,USAGE,UNITS,COST,NOTES")
		.append("\n")
		.append("Electric usage,2017-06-07,00:00,00:59,0.19,kWh,$0.08,TestNote") 
		.append("\n")
		.append("Electric usage,2017-06-07,01:00,01:59,0.14,kWh,$0.08,");
	
		//Only thing we care about is the sb.toString. 
		MockMultipartFile  mock = new MockMultipartFile ("testFile", "fileName", "a",sb.toString().getBytes());
		files.add(mock);
		
		PowerMockito.whenNew(BufferedReader.class).withArguments(Mockito.any()).thenReturn(br);
		Mockito.when(br.readLine()).thenReturn("Name,Guy 2").thenReturn("Address,\"222 way Drive, Somewhere1 MD\"");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		
		Map<Client,List<Record>> readInData =clientLoaderUtil.readInData(files);
		//for(Client client : readInData.keySet()) {
		Client client = readInData.keySet().stream().collect(Collectors.toList()).get(0);
		//Client client = readInData.get();
		assertEquals(client.getAccountNumber(),55414114);
		assertEquals(client.getAddress(),"222 way Drive, Somewhere1 MD");
		assertEquals(client.getName(),"Guy 2");
		assertEquals(client.getService(),"Service 1");
		 
		List<Record> records = readInData.get(client);				

		Record record = records.get(0);
		assertEquals(record.getType(),"Electric usage");
		assertEquals(format.format(record.getRecordKey().getRecordDate()),"2017-06-07");
		assertEquals(record.getRecordKey().getStartTime(),"00:00");
		assertEquals(record.getRecordKey().getEndTime(),"00:59");
		assertEquals(record.getCost(),Double.valueOf("0.08"));
		assertEquals(record.getPower_usage(),Double.valueOf("0.19"));
		assertEquals(record.getNote(),"TestNote");
		
		//Verify we get empty string instead of null
		Record record2 = records.get(1);
		assertEquals(record2.getNote(),"");
			
		//}
	}

}
