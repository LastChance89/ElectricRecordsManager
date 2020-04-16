package com.power.options;

public enum SearchCritera {
	EQUAL("="), 
	LIKE("LIKE"); 
	//BETWEEN("BETWEEN"); Implement me later once I get more front end working. 

	private String critera; 

	private SearchCritera(String critera){
		this.critera = critera;
	}
	
	
	public String getCritera(){
		return critera;
	}
}

