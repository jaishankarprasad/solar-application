package com.rainiersoft.solar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.GridDao;
import com.rainiersoft.solar.dao.impl.ACAndDCPowerDAOImpl.ACDCPowerMapper;
import com.rainiersoft.solar.entity.GridTMEICtbl_Entity;
import com.rainiersoft.solar.entity.GridSGtbl_Entity;
import com.rainiersoft.solar.entity.GridTLtbl_Entity;

@Repository
public class GridDAOImpl implements GridDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<GridTLtbl_Entity> voltageCurrentListByBlock(String block) {
		List<GridTLtbl_Entity> currentVoltageList = new ArrayList<>();

		currentVoltageList = jdbcTemplate.query(
				"select DateAndTime, MPPT1_A,MPPT1_V,MPPT2_A,MPPT2_V, MPPT3_A,MPPT3_V, MPPT4_A,MPPT4_V,  MPPT5_A,MPPT5_V, MPPT6_A,MPPT6_V, MPPT7_A,MPPT7_V, MPPT8_A,MPPT8_V from [tbl_60TL] where DateAndTime=(select MAX(DateAndTime) from [tbl_60TL] where Block='"
						+ block + "' )",
				new GridVoltageCurrentMapper());

		return currentVoltageList;
	}

	public class GridVoltageCurrentMapper implements RowMapper<GridTLtbl_Entity> {

		public GridTLtbl_Entity mapRow(ResultSet rs, int rowNum) throws SQLException {

			GridTLtbl_Entity gridData = new GridTLtbl_Entity();
			gridData.setDateAndTime(rs.getString("DateAndTime"));
			gridData.setmPPT1_ATL(rs.getString("MPPT1_A"));
			gridData.setmPPT1_VTL(rs.getString("MPPT1_V"));
			gridData.setmPPT2_ATL(rs.getString("MPPT2_A"));
			gridData.setmPPT2_VTL(rs.getString("MPPT2_V"));
			gridData.setmPPT3_ATL(rs.getString("MPPT3_A"));
			gridData.setmPPT3_VTL(rs.getString("MPPT3_V"));
			gridData.setmPPT4_ATL(rs.getString("MPPT4_A"));
			gridData.setmPPT4_VTL(rs.getString("MPPT4_V"));
			gridData.setmPPT5_ATL(rs.getString("MPPT5_A"));
			gridData.setmPPT5_VTL(rs.getString("MPPT5_V"));
			gridData.setmPPT6_ATL(rs.getString("MPPT6_A"));
			gridData.setmPPT6_VTL(rs.getString("MPPT6_V"));
			gridData.setmPPT7_ATL(rs.getString("MPPT7_A"));
			gridData.setmPPT7_VTL(rs.getString("MPPT7_V"));
			gridData.setmPPT8_ATL(rs.getString("MPPT8_A"));
			gridData.setmPPT8_VTL(rs.getString("MPPT8_V"));

			return gridData;

		}

	}
// For SG table ...

	@Override
	public List<GridSGtbl_Entity> voltageCurrentSGListByBlock(String block) {
		// TODO Auto-generated method stub
		List<GridSGtbl_Entity> volatgeCurrentSgList = jdbcTemplate.query(
				"select DateAndTime, MPPT_A,MPPT_V  from [tbl_SG60] where DateAndTime=(select MAX(DateAndTime) from [tbl_SG60] where Block='"
						+ block + "' ) ",
				new GridSGtblMapper());

		return volatgeCurrentSgList;
	}

	public class GridSGtblMapper implements RowMapper<GridSGtbl_Entity> {

		@Override
		public GridSGtbl_Entity mapRow(ResultSet rs, int rowNum) throws SQLException {

			GridSGtbl_Entity entity = new GridSGtbl_Entity();
			entity.setDateAndTime(rs.getString("DateAndTime"));
			entity.setMptt_ASG(rs.getString("MPPT_A"));
			entity.setMptt_VSG(rs.getString("MPPT_V"));
			return entity;
		}

	}
// TMEIC TAble ...

	@Override
	public List<GridSGtbl_Entity> voltageCurrentTMEICListByBlock(String block) {
		List<GridSGtbl_Entity> volatgeCurrentTMEICList = jdbcTemplate.query(
				"select DateAndTime, MPPT_A,MPPT_V  from [tbl_TMEIC] where DateAndTime=(select MAX(DateAndTime) from [tbl_TMEIC] where Block='"
						+ block + "' ) ",
				new GridSGtblMapper());

		return volatgeCurrentTMEICList;
	}

	// for Grid Volatge and Current.

	@Override
	public List<?> gridValues(String block) {

		List<?> volatgeCurrentList = new ArrayList<>();

		volatgeCurrentList = jdbcTemplate.query(
				"select DateAndTime, MPPT_A,MPPT_V  from [tbl_TMEIC] where DateAndTime=(select MAX(DateAndTime) from [tbl_TMEIC] where Block='"
						+ block + "' ) ",
				new GridMapperTMEIC());
		if (volatgeCurrentList.isEmpty()) {
			volatgeCurrentList = jdbcTemplate.query(
					"select DateAndTime , MPPT_A,MPPT_V  from [tbl_SG60] where DateAndTime=(select MAX(DateAndTime) from [tbl_SG60] where Block='"
							+ block + "' )",
					new GridMapperSG());
		}
		if (volatgeCurrentList.isEmpty()) {
			volatgeCurrentList = jdbcTemplate.query(
					"select DateAndTime, MPPT1_A,MPPT1_V,MPPT2_A,MPPT2_V, MPPT3_A,MPPT3_V, MPPT4_A,MPPT4_V,  MPPT5_A,MPPT5_V, MPPT6_A,MPPT6_V, MPPT7_A,MPPT7_V, MPPT8_A,MPPT8_V  from [tbl_60TL] where DateAndTime=(select MAX(DateAndTime) from [tbl_60TL] where Block='"
							+ block + "' ) ",
					new GridMapper());
		}

		return volatgeCurrentList;

	}

	public class GridMapper implements RowMapper<GridTLtbl_Entity> {

		public GridTLtbl_Entity mapRow(ResultSet rs, int rowNum) throws SQLException {

			GridTLtbl_Entity gridData = new GridTLtbl_Entity();

			gridData.setDateAndTime(rs.getString("DateAndTime"));
			gridData.setmPPT1_ATL(rs.getString("MPPT1_A"));
			gridData.setmPPT1_VTL(rs.getString("MPPT1_V"));
			gridData.setmPPT2_ATL(rs.getString("MPPT2_A"));
			gridData.setmPPT2_VTL(rs.getString("MPPT2_V"));
			gridData.setmPPT3_ATL(rs.getString("MPPT3_A"));
			gridData.setmPPT3_VTL(rs.getString("MPPT3_V"));
			gridData.setmPPT4_ATL(rs.getString("MPPT4_A"));
			gridData.setmPPT4_VTL(rs.getString("MPPT4_V"));
			gridData.setmPPT5_ATL(rs.getString("MPPT5_A"));
			gridData.setmPPT5_VTL(rs.getString("MPPT5_V"));
			gridData.setmPPT6_ATL(rs.getString("MPPT6_A"));
			gridData.setmPPT6_VTL(rs.getString("MPPT6_V"));
			gridData.setmPPT7_ATL(rs.getString("MPPT7_A"));
			gridData.setmPPT7_VTL(rs.getString("MPPT7_V"));
			gridData.setmPPT8_ATL(rs.getString("MPPT8_A"));
			gridData.setmPPT8_VTL(rs.getString("MPPT8_V"));

			return gridData;

		}
	}

	public class GridMapperTMEIC implements RowMapper<GridTMEICtbl_Entity> {

		public GridTMEICtbl_Entity mapRow(ResultSet rs, int rowNum) throws SQLException {

			GridTMEICtbl_Entity gridData = new GridTMEICtbl_Entity();

			gridData.setDateAndTime(rs.getString("DateAndTime"));
			gridData.setmPPT_A(rs.getString("MPPT_A"));
			gridData.setmPPT_V(rs.getString("MPPT_V"));

			return gridData;

		}
	}

	public class GridMapperSG implements RowMapper<GridSGtbl_Entity> {

		public GridSGtbl_Entity mapRow(ResultSet rs, int rowNum) throws SQLException {

			GridSGtbl_Entity gridData = new GridSGtbl_Entity();

			gridData.setDateAndTime(rs.getString("DateAndTime"));

			gridData.setMptt_ASG(rs.getString("MPPT_A"));
			gridData.setMptt_VSG(rs.getString("MPPT_V"));

			return gridData;

		}
	}

}
