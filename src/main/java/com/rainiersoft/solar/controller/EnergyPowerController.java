package com.rainiersoft.solar.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.EnergyPowerDAO;

import com.rainiersoft.solar.entity.EnergyPower;

@RestController
@RequestMapping("/rest")
public class EnergyPowerController
{
	private static final Logger LOG = LoggerFactory.getLogger(EnergyPowerController.class);
	
	@Autowired(required= true)
	EnergyPowerDAO energyPowerDAO;
	
	@GET
	@RequestMapping(value="/getEnergy")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody	
	public EnergyPower getEnergy()
	{
		EnergyPower energyPower = new EnergyPower();
		energyPower = energyPowerDAO.getEnergy();	
		return energyPower;
	}

}
