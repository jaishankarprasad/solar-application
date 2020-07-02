package com.rainiersoft.solar.dao;

import java.util.List;

import com.rainiersoft.solar.entity.ACAndDCPower;

public interface ACAndDCPowerDAO 
{

	public List<ACAndDCPower> getACAndDCPowerByBlock(String block);
}
