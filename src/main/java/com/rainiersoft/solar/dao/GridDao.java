package com.rainiersoft.solar.dao;

import java.util.List;

import com.rainiersoft.solar.entity.GridTMEICtbl_Entity;
import com.rainiersoft.solar.entity.GridSGtbl_Entity;
import com.rainiersoft.solar.entity.GridTLtbl_Entity;

public interface GridDao {
	
	public List<GridTLtbl_Entity> voltageCurrentListByBlock(String block);
	
	public List<GridSGtbl_Entity>  voltageCurrentSGListByBlock(String block);
	
	public List<GridSGtbl_Entity> voltageCurrentTMEICListByBlock(String block);
	
	public List<?> gridValues(String block);
	
	
	
	

}
