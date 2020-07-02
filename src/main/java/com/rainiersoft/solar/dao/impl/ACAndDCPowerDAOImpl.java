package com.rainiersoft.solar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.ACAndDCPowerDAO;
import com.rainiersoft.solar.entity.ACAndDCPower;

@Repository
public class ACAndDCPowerDAOImpl implements ACAndDCPowerDAO
{
	@Autowired
	JdbcTemplate jdbcTemplate;


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<ACAndDCPower> getACAndDCPowerByBlock(String block) 
	{
		List<ACAndDCPower> acAndDCPowers = new ArrayList<>();

		acAndDCPowers = jdbcTemplate.query("select DateAndTime,AC_PWR,DC_PWR from [tbl_SG60] where DateAndTime=(select MAX(DateAndTime) from [tbl_SG60] where Block='"+block+"')", new ACDCPowerMapper());

		if(acAndDCPowers.isEmpty())
		{
			acAndDCPowers = jdbcTemplate.query("select DateAndTime,AC_PWR,DC_PWR from [tbl_TMEIC] where DateAndTime=(select MAX(DateAndTime) from [tbl_TMEIC] where Block='"+block+"')", new ACDCPowerMapper());
		}
		if(acAndDCPowers.isEmpty())
		{
			acAndDCPowers = jdbcTemplate.query("select DateAndTime,AC_PWR,DC_PWR from [tbl_60TL] where DateAndTime=(select MAX(DateAndTime) from [tbl_60TL] where Block='"+block+"')", new ACDCPowerMapper());
		}

		return acAndDCPowers;
	}
	class ACDCPowerMapper implements RowMapper<ACAndDCPower>
	{

		public ACAndDCPower mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			ACAndDCPower acAndDCPower = new ACAndDCPower();
			acAndDCPower.setAcPower(rs.getString("AC_PWR"));
			acAndDCPower.setDcPower(rs.getString("DC_PWR"));
			acAndDCPower.setDateAndTime(rs.getString("DateAndTime"));
			return acAndDCPower;
		}		
	}

}
