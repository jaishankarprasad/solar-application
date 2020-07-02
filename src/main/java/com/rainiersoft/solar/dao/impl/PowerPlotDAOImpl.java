package com.rainiersoft.solar.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rainiersoft.solar.dao.PowerPlotDAO;
import com.rainiersoft.solar.entity.CurrentPower;
import com.rainiersoft.solar.pojo.DaysCalculator;

@Repository
public class PowerPlotDAOImpl implements PowerPlotDAO 
{
	private static final Logger LOG = LoggerFactory.getLogger(PowerPlotDAOImpl.class);

	@Autowired(required=true)
	private JdbcTemplate jdbcTemplate;


	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<CurrentPower> getCurrentEnergy() 
	{
		DaysCalculator daysCalculator = new DaysCalculator();
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH");  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH"); 
		Date dateNow1 = new Date();  
		System.out.println(formatter1.format(dateNow1));  
		String dateNow = formatter1.format(dateNow1);
		
		String nextDate = dateNow;
		int noOfHoursInBetween = 12;

		//CurrentPower power = null;
		//	for(int i=0;i<noOfHoursInBetween;i++)
		/*List<CurrentPower> currentPowerList = new ArrayList<>();		
			currentPowerList = (List<CurrentPower>) jdbcTemplate.query("select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))\r\n" + 
					", Net_Exp_Engy=AVG(Net_Exp_Engy)\r\n" + 
					"from tbl_WMS\r\n" + 
					"where cast(DateAndTime as Date) = cast(getdate() as Date)\r\n" + 
					"group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))" ,new FetchEnergyMapper());
		
*/
		
		List<CurrentPower> currentPowerList = new ArrayList<>();		
		currentPowerList = (List<CurrentPower>) jdbcTemplate.query("select DateAndTime=dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))\r\n" + 
				"					, Net_Exp_Engy=AVG(Net_Exp_Engy)\r\n" + 
				"					from tbl_WMS\r\n" + 
				"					where cast(DateAndTime as Date) = cast(getdate() as Date) and (datepart(hh,DateAndTime) > 5 and datepart(hh,DateAndTime) < 20 )\r\n" + 
				"					group by dateadd(hh,datepart(hh,DateAndTime), cast(CAST(DateAndTime as date) as datetime))" ,new FetchEnergyMapper());
	
		return currentPowerList;
	}

	class FetchEnergyMapper implements RowMapper<CurrentPower>
	{
		public CurrentPower mapRow(ResultSet rs, int arg1) throws SQLException
		{ 
			CurrentPower currentPower=new CurrentPower();
			currentPower.setNetExpEngy(rs.getString("Net_Exp_Engy"));

			currentPower.setEnergyAtDate(rs.getString("DateAndTime"));
			return currentPower;
		}
	}
}
