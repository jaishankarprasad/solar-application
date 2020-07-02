package com.rainiersoft.solar.dao;

import java.util.List;

import com.rainiersoft.solar.pojo.CurrentACPower;

public interface CurrentACPowerDAO {
	
	public List<CurrentACPower> getCurrentACPower(String block);

}
