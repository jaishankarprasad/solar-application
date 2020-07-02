package com.rainiersoft.solar.controller;




import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.AlarmDao;
import com.rainiersoft.solar.entity.AlarmEntity;
import com.rainiersoft.solar.entity.AlarmObjectEntity;

@RestController
@RequestMapping("/rest")

public class AlarmController {
	
	@Autowired(required=true)
	AlarmDao alarmDao;
	
	@RequestMapping(value="/getAlarmData")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	
	public AlarmEntity getAlarmData() {
		AlarmEntity alarmDetails= new AlarmEntity();
		alarmDetails=alarmDao.getAlarmDetails();
		return alarmDetails; 
	}
	
	
	@RequestMapping(value="/getAlarmObjectData")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	
	public AlarmObjectEntity getAlarmObjectData() {
		AlarmObjectEntity alarmDetailsObject= new AlarmObjectEntity();
		alarmDetailsObject=alarmDao.getAlarmObjectDetails();
		return alarmDetailsObject; 
	}


}
