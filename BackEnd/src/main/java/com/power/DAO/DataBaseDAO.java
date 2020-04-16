package com.power.DAO;

/*
 * Interface that contains basic sql methods. 
 */
public interface DataBaseDAO {

	void save(String sql);
	
	void update(String sql);
	
	void DeleteRecord(String sql);
	
}
