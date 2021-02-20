package com.power.services.Impl;

import java.sql.Connection;
import java.sql.DriverManager;

//Think this is left over, probably can remove. 
public class DatabaseService_SQLITE{
	
	public static void Connect(){
		
		Connection conn = null; 
		try{
			String databaseURL = "jdbc:sqlite:D:\\databases\\power.db";
			conn = DriverManager.getConnection(databaseURL);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			try{
				if (conn != null){
					conn.close();
				}
			}
			catch (Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
		}
	}

}
