package com.rainiersoft.solar.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.AuthenticationDAO;
import com.rainiersoft.solar.dao.impl.AuthenticationDAOImpl;
import com.rainiersoft.solar.entity.UserInfo;
import com.rainiersoft.solar.response.LoginResponse;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


@RestController
@RequestMapping("/rest")
public class AuthenticationController
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired(required=true)
	AuthenticationDAO authenticationDAO;

	@RequestMapping(value="/hello")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String helo()
	{
		return "Jaishankar prasad";
	}


	@RequestMapping(value="/login/{username}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public LoginResponse doLogin(@PathVariable(value = "username")  String username,@PathVariable(value = "password")  String password)throws ParseException 
	{
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		LoginResponse loginResponse = authenticationDAO.getDBInfoRes(userInfo);
		try {
			if(loginResponse!=null)

			{
				loginResponse.setSuccessMessage("Login successful");
				loginResponse.setStatus("TRUE");
				loginResponse.setFirstname(loginResponse.getFirstname());
				loginResponse.setLastname(loginResponse.getLastname());
			}
			else 
			{
				LoginResponse loginResponse1 =new LoginResponse();
				loginResponse1.setErrorMessage("Invalid credentials");
				loginResponse1.setStatus("FALSE");
				return loginResponse1;
			}
		}
		catch(NullPointerException ne) 
		{
			System.out.println("exception occured"+ne);
		}
		return loginResponse;
	}
}
/*	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired(required=true)
	AuthenticationDAOImpl authenticationDAO;

	@RequestMapping(value="/getAuthentication", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getAuthentication(@RequestBody UserInfo userInfo)
	{
		LOG.info("In getDBInfo Service Controller class");

		ResponseMessage res = new ResponseMessage();

		DBResponse loginResponse =(DBResponse) authenticationDAO.getDBInfo(userInfo);
		if(loginResponse != null)
		{			
			if(loginResponse.getPassword() != null && loginResponse.getPassword().equalsIgnoreCase(userInfo.getPassword()))
			{
				res.setStatus(true);

				res.setMessage("You have Logged in Suucessfully");
				res.setUsername(loginResponse.getUsername());
				res.setRoleid(loginResponse.getRoleid());
			}
			else
			{
				res.setStatus(false);
				res.setMessage("The password you have entered is not correct");
			}
		}
		else
		{
			res.setStatus(false);
			res.setMessage("The username you have entered is not correct");
		}		
		return res;
	}*/

/*	@Autowired(required=true)
	AuthenticationDAOImpl authenticationDAOImpl;


	@RequestMapping(value="/login", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LoginResponse doLogin(@RequestBody UserInfo userInfo)throws ParseException  {

		LoginResponse loginResponse = authenticationDAOImpl.getDBInfo(userInfo);
		try {
			if(loginResponse!=null)

			{
				loginResponse.setSuccessMessage("Login successful");
				loginResponse.setStatus("TRUE");
				loginResponse.setFirstname(loginResponse.getFirstname());
				loginResponse.setLastname(loginResponse.getLastname());
			}
			else {
				LoginResponse lR=new LoginResponse();
				lR.setErrorMessage("Invalid credentials");
				lR.setStatus("FALSE");
				return lR;
			}
		}
		catch(NullPointerException ne) {
			System.out.println("exception occured"+ne);
		}
		return loginResponse;
	}*/


