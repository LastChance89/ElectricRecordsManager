package com.power.options;

public enum SearchOpt {
	ACC_NUM("ACCOUNT_NUMBER"), 
	NAME("NAME"), 
	//SERVICE("SERVICE") Determine if we want more than 1 for future reference?
	;
	
	private String optval; 
	
	private SearchOpt(String optval){
		this.optval = optval;
	}
	
	public String getOptVal(){
		return optval;
	}
	
}
