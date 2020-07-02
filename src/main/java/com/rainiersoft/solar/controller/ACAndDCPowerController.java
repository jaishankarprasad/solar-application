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

import com.rainiersoft.solar.dao.ACAndDCPowerDAO;
import com.rainiersoft.solar.entity.ACAndDCPower;

@RestController
@RequestMapping("/rest")
public class ACAndDCPowerController 
{
	@Autowired
	ACAndDCPowerDAO aCAndDCPowerDAO;
	
	@RequestMapping(value = "/getACAndDCPowerByBlock/{block}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<ACAndDCPower> getACAndDCPowerByBlock(@PathVariable(value="block") String block)
	{
		List<ACAndDCPower> acAndDCPowers = new ArrayList<>();
		
		acAndDCPowers = aCAndDCPowerDAO.getACAndDCPowerByBlock(block);
		
		return acAndDCPowers;
		
	}
}
