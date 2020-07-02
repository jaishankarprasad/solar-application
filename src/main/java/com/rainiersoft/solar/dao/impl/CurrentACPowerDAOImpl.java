package com.rainiersoft.solar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.CurrentACPowerDAO;
import com.rainiersoft.solar.pojo.CurrentACPower;

@Repository
public class CurrentACPowerDAOImpl implements CurrentACPowerDAO
{

	@Autowired(required = true)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<CurrentACPower> getCurrentACPower(String block) 
	{
		List<CurrentACPower> currentACPowers = new ArrayList<>();
		
		currentACPowers = jdbcTemplate.query("select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime)) ,\r\n" + 
				"				 AC_PWR=AVG(AC_PWR)\r\n" + 
				"				from [tbl_SG60] \r\n" + 
				"				where cast(DateAndTime as Date) = cast(getdate() as Date) and Block = '"+block+"'" + 
				"				group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))  " , new ACPowerMapper());
		
		
		if(currentACPowers.isEmpty())
		{
			currentACPowers = jdbcTemplate.query("select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime)) ,\r\n" + 
					"				 AC_PWR=AVG(AC_PWR)\r\n" + 
					"				from [tbl_60TL] \r\n" + 
					"				where cast(DateAndTime as Date) = cast(getdate() as Date) and Block = '"+block+"'" + 
					"				group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))  " , new ACPowerMapper());
		}
		

		if(currentACPowers.isEmpty())
		{
			currentACPowers = jdbcTemplate.query("select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime)) ,\r\n" + 
					"				 AC_PWR=AVG(AC_PWR)\r\n" + 
					"				from [tbl_TMEIC] \r\n" + 
					"				where cast(DateAndTime as Date) = cast(getdate() as Date) and Block = '"+block+"'" + 
					"				group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))  " , new ACPowerMapper());
		}
		
		return currentACPowers;
	}
	
	class ACPowerMapper implements RowMapper<CurrentACPower>
	{
		public CurrentACPower mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CurrentACPower currentACPower=new CurrentACPower();
			currentACPower.setAcPower(rs.getString("AC_PWR"));
			currentACPower.setDateAndTime(rs.getString("DateAndTime"));
			return currentACPower;
		
		}
	}

}
