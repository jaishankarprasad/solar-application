package com.rainiersoft.solar.controller;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.BlockTypeDao;
import com.rainiersoft.solar.dao.impl.AuthenticationDAOImpl;
import com.rainiersoft.solar.entity.BlockTypeEntity;



@RestController
@RequestMapping("/rest")
public class BlockTypeController {
	private static final Logger LOG = LoggerFactory.getLogger(BlockTypeController.class);
	
	
	@Autowired(required=true)
	BlockTypeDao blockTypeDao;
	//@RequestMapping(value="/getBlockType", method = RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)

	@RequestMapping(value="/getBlockType")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	@GET
	public List<BlockTypeEntity> getBlockType() {
		//BlockTypeEntity blockDetails= new BlockTypeEntity();
		List<BlockTypeEntity> blockDetails= new ArrayList<>();
		blockDetails= blockTypeDao.fetchBlockTypeDetails();
		return blockDetails;
		
	}
}
