package com.rainiersoft.solar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.PowerPlotDAO;
import com.rainiersoft.solar.dao.impl.PowerPlotDAOImpl;
import com.rainiersoft.solar.entity.CurrentPower;
import com.rainiersoft.solar.entity.EnergyPower;

@RestController
@RequestMapping("/rest")
public class PowerPlotController 
{
	
	private static final Logger LOG = LoggerFactory.getLogger(PowerPlotController.class);
	@Autowired
	PowerPlotDAO powerPlotDAO;

	@GET
	@RequestMapping(value="/getCurrentEnergy")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	
	public List<CurrentPower> getCurrentEnergy()
	{
		List<CurrentPower> currentPower = new ArrayList<>();
		currentPower = powerPlotDAO.getCurrentEnergy();
		
		return currentPower;
	}

}
