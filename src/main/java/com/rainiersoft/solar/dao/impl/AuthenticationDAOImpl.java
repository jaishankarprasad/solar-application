package com.rainiersoft.solar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rainiersoft.solar.dao.AuthenticationDAO;
import com.rainiersoft.solar.entity.UserInfo;
import com.rainiersoft.solar.response.LoginResponse;

@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationDAOImpl.class);
	
	@Autowired(required=true)
	private JdbcTemplate jdbcTemplate;


	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public LoginResponse getDBInfoRes(UserInfo userInfo)
	{
		List<LoginResponse>login = jdbcTemplate.query("select username,password from logininfo where username ='"+userInfo.getUsername()+"' and password = '"+userInfo.getPassword()+"'",new DetailsMapper());

		return login.size()>0?login.get(0):null;

	}
}
class DetailsMapper implements RowMapper<LoginResponse>{
	public LoginResponse mapRow(ResultSet rs, int arg1) throws SQLException
	{
		LoginResponse loginResponse=new LoginResponse();
		loginResponse.setFirstname(rs.getString(1));
		loginResponse.setLastname(rs.getString(2));
		return loginResponse;
	}
}

// Below Code is Hibernate Mapping Fetching 


	/*	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationDAOImpl.class);

	 @Autowired
	SessionFactory sessionFactory;

	Transaction transaction;

	public DBResponse getDBInfo(UserInfo userInfo)  
	{
		DBResponse dbResponse = null;
		UserInfo dbRecord = null;
		try
		{
			LOG.info("In getAuthentication Method");	

			Session session = sessionFactory.getCurrentSession();
			transaction  = session.beginTransaction();

			dbRecord = (UserInfo) session.byId(UserInfo.class).load(userInfo.getUsername());	

			dbResponse = new DBResponse();
			if(dbRecord != null)
			{
				dbResponse.setUsername(dbRecord.getUsername());
				dbResponse.setPassword(dbRecord.getPassword());
				dbResponse.setRoleid(dbRecord.getRole());
			}
			else
			{
				dbResponse = null;
			}
			session.clear();
		}
		catch(HibernateException he)
		{
			LOG.error("Error Occured in getAuthentication Process"+he);
			he.printStackTrace();
		}

		return dbResponse;
	}
	 */

