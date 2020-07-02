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

import com.rainiersoft.solar.dao.CurrentPOADAO;
import com.rainiersoft.solar.entity.CurrentPOA;

@RestController
@RequestMapping("/rest")
public class CurrentPOAController 
{
	private static final Logger LOG = LoggerFactory.getLogger(CurrentPOAController.class);

		@Autowired
		CurrentPOADAO currentPOADAO;
		
		@GET
		@RequestMapping(value="/getCurrentPOA")
		@Produces(MediaType.APPLICATION_JSON)
		@ResponseBody
		
		public List<CurrentPOA> getCurrentEnergy()
		{
			List<CurrentPOA> currentPOA = new ArrayList<>();
			currentPOA = currentPOADAO.getCurrentPOA();
			
			return currentPOA;
		}
	
}
