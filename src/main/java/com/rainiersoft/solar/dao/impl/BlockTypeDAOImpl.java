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

import com.rainiersoft.solar.dao.BlockTypeDao;
import com.rainiersoft.solar.entity.BlockTypeEntity;
import com.rainiersoft.solar.entity.CurrentPower;
import com.rainiersoft.solar.entity.EnergyPower;

@Repository
public class BlockTypeDAOImpl implements BlockTypeDao {
	private static final Logger logger = LoggerFactory.getLogger(BlockTypeDAOImpl.class);

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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<BlockTypeEntity> fetchBlockTypeDetails() {
		List<BlockTypeEntity> blockTypeEntityList = new ArrayList<>();
		
		blockTypeEntityList= (List<BlockTypeEntity>) JdbcTemplate.query(
				"SELECT distinct Block FROM tbl_60TL UNION SELECT distinct Block FROM tbl_SG60 UNION SELECT distinct Block FROM tbl_TMEIC",
				new FetchMapper());

		/*return blockTypeEntityList.size()>0 ? blockTypeEntityList.get(0):null;*/
		return   blockTypeEntityList;
	}

	class FetchMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			BlockTypeEntity entity = new BlockTypeEntity();
			entity.setBlockType(rs.getString("Block"));
			return entity;
		}
		
	}
	
}
