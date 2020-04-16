package com.power.DAO;

import java.util.List;
import java.util.Map;

import com.power.models.GridMeta;

public interface GridMetaDAO {
	public Map<Integer,List<Map<String,String>>> getGridMeta();
}
