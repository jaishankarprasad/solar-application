package com.rainiersoft.solar.dao;

import com.rainiersoft.solar.entity.UserInfo;
import com.rainiersoft.solar.response.LoginResponse;

public interface AuthenticationDAO {
	
	public LoginResponse getDBInfoRes(UserInfo userInfo);

}
