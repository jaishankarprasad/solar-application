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

import com.rainiersoft.solar.dao.CurrentPOADAO;
import com.rainiersoft.solar.entity.CurrentPOA;

@Repository
public class CurrentPOADAOImpl implements CurrentPOADAO
{
	private static final Logger LOG = LoggerFactory.getLogger(CurrentPOADAOImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<CurrentPOA> getCurrentPOA() 
	{	
		List<CurrentPOA> currentPOAList = new ArrayList<>();	
		
		currentPOAList = (List<CurrentPOA>) jdbcTemplate.query("select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))\r\n" + 
				", WMS1_POA=AVG(WMS1_POA)\r\n" + 
				"from tbl_WMS\r\n" + 
				"where cast(DateAndTime as Date) = cast(getdate() as Date)\r\n" + 
				"group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))" ,new FetchPOAMapper());
	

	return currentPOAList;
}

class FetchPOAMapper implements RowMapper<CurrentPOA>
{
	public CurrentPOA mapRow(ResultSet rs, int arg1) throws SQLException
	{
		CurrentPOA currentPOA=new CurrentPOA();
		currentPOA.setWms1POA(rs.getString("WMS1_POA"));
		currentPOA.setDateAndTime(rs.getString("DateAndTime"));
		return currentPOA;
	
	}
}

}
