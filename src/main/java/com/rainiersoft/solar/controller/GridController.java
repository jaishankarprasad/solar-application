package com.rainiersoft.solar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.GridDao;
import com.rainiersoft.solar.entity.GridTMEICtbl_Entity;
import com.rainiersoft.solar.entity.GridSGtbl_Entity;
import com.rainiersoft.solar.entity.GridTLtbl_Entity;

@RestController
@RequestMapping("/rest")
public class GridController {
	@Autowired
	GridDao gridDao;
	@RequestMapping(value="getTLGridValuesByBlock/{block}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@ResponseBody
	public List<GridTLtbl_Entity> getGridValues(@PathVariable String block){
		List<GridTLtbl_Entity> gridList= new ArrayList<>();
		gridList=gridDao.voltageCurrentListByBlock(block);
		return gridList;
		
	}

	// SG Data ...
	
	@RequestMapping(value="getSGGridValuesByBlock/{block}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@ResponseBody
	public List<GridSGtbl_Entity> getSGCurrentVoltage(@PathVariable String block){
		 List<GridSGtbl_Entity> gridSgList= new ArrayList<>();
		 gridSgList= gridDao.voltageCurrentSGListByBlock(block);
		 return gridSgList;
		
	}
	
	// TMEIC Data ...
	@RequestMapping(value="getTMEICGridValuesByBlock/{block}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@ResponseBody
	public List<GridSGtbl_Entity> getTMEICCurrentVoltage(@PathVariable String block){
		 List<GridSGtbl_Entity> gridTMEICList= new ArrayList<>();
		 gridTMEICList= gridDao.voltageCurrentTMEICListByBlock(block);
		 return gridTMEICList;
		
	}
	
	// Grid In one service ...
	@RequestMapping(value="gridValuesByBlock/{block}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@ResponseBody
	public List<?> gridValuesByBlock(@PathVariable String block){
		 List<?> gridValues= new ArrayList<>();
		 gridValues= gridDao.gridValues(block);
		 return gridValues;
		
	}
	
}
