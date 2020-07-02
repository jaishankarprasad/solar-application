package com.rainiersoft.solar.dao.impl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.AlarmDao;
import com.rainiersoft.solar.entity.AlarmEntity;
import com.rainiersoft.solar.entity.AlarmObjectEntity;
import com.rainiersoft.solar.entity.EnergyPower;

@Repository
public class AlarmDetailsDAOImpl implements AlarmDao {
	private static final Logger logger = LoggerFactory.getLogger(AlarmDetailsDAOImpl.class);

	@Autowired(required = true)
	private JdbcTemplate JdbcTemplate;

	

	public JdbcTemplate getJdbcTemplate() {
		return JdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		JdbcTemplate = jdbcTemplate;
	}

	
	
	public static Logger getLogger() {
		return logger;
	}

	@Override
	public AlarmEntity getAlarmDetails() {
	/*List<AlarmEntity>	alarmListData= JdbcTemplate.query("select * from ConditionEvent where EventTimeStamp=(select max(EventTimeStamp) from ConditionEvent)", new FetchMapper());*/
		List<AlarmEntity>	alarmListData=JdbcTemplate.query("select top 100 SourceName,EventID, EventTimeStamp,Message,Active from V_Alarm \r\n" + 
				"where datepart(YEAR, EventTimeStamp)=datepart(year,GETDATE())\r\n" + 
				"and  datepart(MONTH, EventTimeStamp)=datepart(MONTH,GETDATE()) and  datepart(DAY, EventTimeStamp)=datepart(DAY,GETDATE()) order by EventTimeStamp DESC\r\n" + 
				"", new FetchMapper());
		
		return alarmListData.size()>0?alarmListData.get(0):null;
	}

	class FetchMapper implements RowMapper<AlarmEntity>
	{

		@Override
		public AlarmEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			AlarmEntity alarmEntityList= new AlarmEntity();
			
			alarmEntityList.setAlarmMessage(rs.getString("Message"));
			alarmEntityList.setEventDateAndTime(rs.getString("EventTimeStamp"));
		
			alarmEntityList.setEventSourceName(rs.getString("SourceName"));
		//	alarmEntityList.setEvetId(rs.getString("EventID"));
			alarmEntityList.setAlarmStatus(rs.getInt("Active"));
			return alarmEntityList;
		}
		
	}
	// Alarm Object .......

	@Override
	public AlarmObjectEntity getAlarmObjectDetails() {
		AlarmObjectEntity objectEntity= new AlarmObjectEntity();
		
	/*	List<AlarmEntity> alarmDetails= JdbcTemplate.query("select top 100 SourceName, EventTimeStamp,Message,Active from V_Alarm \r\n" + 
				"where datepart(YEAR, EventTimeStamp)=datepart(year,GETDATE())\r\n" + 
				"and  datepart(MONTH, EventTimeStamp)=datepart(MONTH,GETDATE()) and  datepart(DAY, EventTimeStamp)=datepart(DAY,GETDATE()) order by EventTimeStamp DESC", new AlarmFetchMapper());
		
		*/
		
		List<AlarmEntity> alarmDetails=JdbcTemplate.query("select  top 100 SourceName, EventTimeStamp,Message,Active  from V_Alarm order by EventTimeStamp DESC", new AlarmFetchMapper());
		objectEntity.setMessage(" Today Alarm received");
		objectEntity.setStatus(true);
		objectEntity.setAlarmObject(alarmDetails);
		return objectEntity;
	}
	
	
	class AlarmFetchMapper implements RowMapper<AlarmEntity>
	{

		@Override
		public AlarmEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			AlarmEntity alarmEntityList= new AlarmEntity();
			
			alarmEntityList.setAlarmMessage(rs.getString("Message"));
			alarmEntityList.setEventDateAndTime(rs.getString("EventTimeStamp"));
			//alarmEntityList.setAlarmStatus(rowNum.getInt());
			alarmEntityList.setEventSourceName(rs.getString("SourceName"));
		//	alarmEntityList.setEvetId(rs.getString("EventID"));
			alarmEntityList.setAlarmStatus(rs.getInt("Active"));
			return alarmEntityList;
		}
	}
	
	
}


	

