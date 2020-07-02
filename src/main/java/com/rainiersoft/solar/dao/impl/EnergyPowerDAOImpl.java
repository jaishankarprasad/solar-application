package com.rainiersoft.solar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.EnergyPowerDAO;
import com.rainiersoft.solar.entity.EnergyPower;

@Repository
public class EnergyPowerDAOImpl implements EnergyPowerDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(EnergyPowerDAOImpl.class);
	
	@Autowired(required=true)
	private JdbcTemplate jdbcTemplate;


	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public EnergyPower getEnergy()
	{
		LOG.info("In getEnegry  Implementation Class");
		List<EnergyPower> energyPower = jdbcTemplate.query("select * from tbl_WMS  where DateAndTime  = ( SELECT  max(DateAndTime) FROM tbl_WMS)" ,new FetchMapper());

		return energyPower.size()>0?energyPower.get(0):null;

	}
}
class FetchMapper implements RowMapper<EnergyPower>
{
	public EnergyPower mapRow(ResultSet rs, int arg1) throws SQLException
	{
		EnergyPower energyPower=new EnergyPower();
		energyPower.setNetExpEnergy(rs.getString("Net_Exp_Engy"));
		energyPower.setPower(rs.getString("Power"));
		energyPower.setPerfomanceRatio(rs.getString("PR"));
		energyPower.setNetImpEnergy(rs.getString("Net_IMP_Engy"));
		energyPower.setDateAndTime(rs.getString("DateAndTime"));
		return energyPower;
	}

}
