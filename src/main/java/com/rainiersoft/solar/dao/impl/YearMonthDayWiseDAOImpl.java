package com.rainiersoft.solar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.YearMonthDayWiseDAO;
import com.rainiersoft.solar.entity.DaysFetcher;
import com.rainiersoft.solar.entity.YearMonthDayWise;
import com.rainiersoft.solar.entity.YearWiseDayPower;

@Repository
public class YearMonthDayWiseDAOImpl implements YearMonthDayWiseDAO 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<YearMonthDayWise> getYearWiseList() 
	{
		List<YearMonthDayWise> yearWiseList = new ArrayList<>();

		yearWiseList = jdbcTemplate.query("SELECT *\r\n" + 
				"FROM (SELECT [Power],This_Year,This_Month, DateAndTime\r\n" + 
				"          , ROW_NUMBER() OVER (PARTITION BY YEAR(DateAndTime) ORDER BY DateAndTime DESC) 'RowRank'\r\n" + 
				"      FROM [tbl_WMS]  \r\n" + 
				"     ) sub 	 WHERE RowRank = 1 ", new YearMapper());

		return yearWiseList;
	}

	class YearMapper implements RowMapper<YearMonthDayWise>
	{

		public YearMonthDayWise mapRow(ResultSet rs, int arg1) throws SQLException
		{
			YearMonthDayWise yearWise = new YearMonthDayWise();
		/*	yearWise.setDateAndTime(rs.getString("DateAndTime"));
			yearWise.setPower(rs.getString("Power"));
			yearWise.setThisMonth(rs.getString("This_Month"));
			yearWise.setThisYear(rs.getString("This_Year"));*/
			
			yearWise.setDateAndTime(rs.getString("DateAndTime"));
			yearWise.setPower(rs.getFloat("Power"));
			yearWise.setThisMonth(rs.getFloat("This_Month"));
			yearWise.setThisYear(rs.getFloat("This_Year"));
			return yearWise;
		}
	}
	
	
	public List<YearMonthDayWise> getListByYearForMonths(String year) 
	{
		List<YearMonthDayWise> yearWiseList = new ArrayList<>();

		yearWiseList = jdbcTemplate.query("SELECT *\r\n" + 
				"FROM (SELECT [Power],This_Year,This_Month, DateAndTime\r\n" + 
				"          , ROW_NUMBER() OVER (PARTITION BY YEAR(DateAndTime), Month(DateAndTime) ORDER BY DateAndTime DESC) 'RowRank'\r\n" + 
				"      FROM [tbl_WMS]  \r\n" + 
				"     ) sub 	 WHERE RowRank = 1 AND DATEPART(yyyy,DateAndTime) = '"+year+"'", new MonthWiseMapper());

		return yearWiseList;
	}

	class MonthWiseMapper implements RowMapper<YearMonthDayWise>
	{

		public YearMonthDayWise mapRow(ResultSet rs, int arg1) throws SQLException
		{
			YearMonthDayWise yearWise = new YearMonthDayWise();
			yearWise.setDateAndTime(rs.getString("DateAndTime"));
			yearWise.setPower(rs.getFloat("Power"));
			yearWise.setThisMonth(rs.getFloat("This_Month"));
			yearWise.setThisYear(rs.getFloat("This_Year"));
			return yearWise;
		}
	}
	
	
	public List<YearMonthDayWise> getListByMonthsForDays(DaysFetcher daysFetcher) 
	{
		List<YearMonthDayWise> yearWiseList = new ArrayList<>();
		
		String year = daysFetcher.getYear();
		String month = daysFetcher.getMonth();

		yearWiseList = jdbcTemplate.query("	 	 	 	 	 	  SELECT *\r\n" + 
				"FROM (SELECT [Power],This_Year,This_Month, DateAndTime\r\n" + 
				"          , ROW_NUMBER() OVER (PARTITION BY YEAR(DateAndTime), Month(DateAndTime),DAY(DateAndTime) ORDER BY DateAndTime DESC) 'RowRank'\r\n" + 
				"      FROM [tbl_WMS]  \r\n" + 
				"     ) sub 	 WHERE RowRank = 1 AND DATEPART(yyyy,DateAndTime) = '"+year+"' AND DATEPART(mm,DateAndTime) = '"+month+"'", new DayWiseMapper());

		return yearWiseList;
	}

	class DayWiseMapper implements RowMapper<YearMonthDayWise>
	{

		public YearMonthDayWise mapRow(ResultSet rs, int arg1) throws SQLException
		{
			YearMonthDayWise yearWise = new YearMonthDayWise();
			yearWise.setDateAndTime(rs.getString("DateAndTime"));
			yearWise.setPower(rs.getFloat("Power"));
			yearWise.setThisMonth(rs.getFloat("This_Month"));
			yearWise.setThisYear(rs.getFloat("This_Year"));
			return yearWise;
		}
	}


	
	// Year Wise Day Power ...
	
	@Override
	public List<YearWiseDayPower> getYearWiseDayPower(String date) {
		// TODO Auto-generated method stub
		
		List<YearWiseDayPower> powerYearDay= new ArrayList<>();
		powerYearDay= jdbcTemplate.query("\r\n" + 
				"select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))\r\n" + 
				", Power=AVG(Power)\r\n" + 
				"from tbl_WMS\r\n" + 
				"where cast(DateAndTime as Date) = cast('"+date+"' as Date) and (datepart(hh,DateAndTime) > 1 and datepart(hh,DateAndTime) < 20 )\r\n" + 
				"group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))", new YearDayWisePowerMapper());
		return powerYearDay;
	}
	class YearDayWisePowerMapper implements RowMapper<YearWiseDayPower>
	{

		public YearWiseDayPower mapRow(ResultSet rs, int arg1) throws SQLException
		{
			YearWiseDayPower yearWisePower = new YearWiseDayPower();
			yearWisePower.setDateAndTime(rs.getString("DateAndTime"));
			yearWisePower.setPower(rs.getFloat("Power"));
			
			return yearWisePower;
		}
	}


	
	
}
