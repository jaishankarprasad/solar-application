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

import com.rainiersoft.solar.dao.CurrentACPowerDAO;
import com.rainiersoft.solar.pojo.CurrentACPower;

@RestController
@RequestMapping("/rest")
public class CurrentACPowerController 
{
	@Autowired
	CurrentACPowerDAO currentACPowerDAO;
	
	@RequestMapping(value = "/getCurrentACPower/{block}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<CurrentACPower> getCurrentACPower(@PathVariable(value="block") String block)
	{
		List<CurrentACPower> currentACPowerList = new ArrayList<>();
		
		currentACPowerList  = currentACPowerDAO.getCurrentACPower(block);
		
		return currentACPowerList;
	}

}
